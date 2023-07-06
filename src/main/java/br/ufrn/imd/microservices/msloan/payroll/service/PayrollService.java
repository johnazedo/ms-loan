package br.ufrn.imd.microservices.msloan.payroll.service;

import br.ufrn.imd.microservices.msloan.core.client.CurrentAccountClient;
import br.ufrn.imd.microservices.msloan.core.client.PaymentClient;
import br.ufrn.imd.microservices.msloan.core.exceptions.NotFoundException;
import br.ufrn.imd.microservices.msloan.core.exceptions.PayrollException;
import br.ufrn.imd.microservices.msloan.core.log.Log;
import br.ufrn.imd.microservices.msloan.core.log.LogSender;
import br.ufrn.imd.microservices.msloan.feesetting.dto.FeeDto;
import br.ufrn.imd.microservices.msloan.feesetting.model.Fee;
import br.ufrn.imd.microservices.msloan.feesetting.repository.FeeRepository;
import br.ufrn.imd.microservices.msloan.feesetting.service.FeeService;
import br.ufrn.imd.microservices.msloan.payroll.dto.CheckDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollOutDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollPostDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollPutDto;
import br.ufrn.imd.microservices.msloan.payroll.mapper.PayrollMapper;
import br.ufrn.imd.microservices.msloan.payroll.model.Contract;
import br.ufrn.imd.microservices.msloan.payroll.model.Payroll;
import br.ufrn.imd.microservices.msloan.payroll.model.PayrollStatus;
import br.ufrn.imd.microservices.msloan.payroll.repository.ContractRepository;
import br.ufrn.imd.microservices.msloan.payroll.repository.PayrollRepository;
import br.ufrn.imd.microservices.msloan.requirementdetail.dto.RequirementDetailDto;
import br.ufrn.imd.microservices.msloan.simulation.PayrollLoanSimulatedDto;
import br.ufrn.imd.microservices.msloan.simulation.PayrollLoanSimulationDto;
import feign.RetryableException;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PayrollService {

    private final FeeRepository feeRepository;
    private final FeeService feeService;
    private final ContractRepository contractRepository;
    private final PayrollRepository payrollRepository;
    private final PayrollMapper payrollMapper;
    private final CurrentAccountClient currentAccountClient;
    private final PaymentClient paymentClient;
    private final LogSender logger;

    public PayrollService(FeeRepository feeRepository,
                          FeeService feeService,
                          CurrentAccountClient currentAccountClient,
                          ContractRepository contractRepository,
                          PayrollRepository payrollRepository,
                          PayrollMapper payrollMapper,
                          PaymentClient paymentClient,
                          LogSender logger) {
        this.feeRepository = feeRepository;
        this.feeService = feeService;
        this.currentAccountClient = currentAccountClient;
        this.contractRepository = contractRepository;
        this.payrollRepository = payrollRepository;
        this.payrollMapper = payrollMapper;
        this.paymentClient = paymentClient;
        this.logger = logger;
    }

    public PayrollLoanSimulatedDto simulatePayrollLoan(PayrollLoanSimulationDto simulation) {
        BigDecimal approved = approvedValueByAccount(simulation.accountId());

        if (simulation.totalValue().compareTo(approved) > 0) {
            throw new PayrollException("Usuário não pode solicitar empréstimo para este valor.");
        }

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("simulation")
                .setContext("main")
                .setLevel(LogLevel.INFO)
                .setMessage("simulation payroll by: " + simulation.accountId())
                .setIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRemoteAddr())
                .build());

        return payrollLoanCalculate(simulation.totalValue(), simulation.installments());

    }

    private PayrollLoanSimulatedDto payrollLoanCalculate(BigDecimal totalValue, int installments) {
        Fee fee = feeRepository.findByActiveEquals(true);

        if(Objects.isNull(fee)){
            FeeDto defaultFee = new FeeDto(null, BigDecimal.TEN, null, true);
            feeService.save(defaultFee);

            fee = feeRepository.findByActiveEquals(true);
        }

        BigDecimal feeOverSimulationTotalValue = totalValue
                .multiply(fee.getPercentage()
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR))
                .setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal totalValueWithFee = totalValue.add(feeOverSimulationTotalValue)
                .setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal installmentsSimulation = totalValueWithFee
                .divide(BigDecimal.valueOf(installments), 2, RoundingMode.FLOOR)
                .setScale(2, RoundingMode.HALF_DOWN);

        return new PayrollLoanSimulatedDto(installmentsSimulation,
                fee.getPercentage(),
                totalValueWithFee,
                installments);
    }

    private BigDecimal approvedValueByAccount(Integer accountId) {
        BigDecimal defaultApprovedValue = BigDecimal.valueOf(10_000);
        BigDecimal calculatedValue = BigDecimal.ZERO;
        try {
            List<CheckDto> checks = currentAccountClient.allChecksByAccount(accountId);
            calculatedValue = checks.stream()
                    .max(Comparator.comparing(CheckDto::limite))
                    .map(checkDto -> checkDto.limite().multiply(BigDecimal.TEN))
                    .orElseThrow(NoSuchElementException::new);
        } catch (RetryableException e) {
            logger.send(new Log.LogBuilder()
                    .setClazz(currentAccountClient.getClass().getName())
                    .setMethod("approvedValueByAccount")
                    .setContext("main")
                    .setLevel(LogLevel.ERROR)
                    .setMessage("allChecksByAccount in currentAccount offline")
                    .setIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                            .getRequest().getRemoteAddr())
                    .build());
        }

        if (calculatedValue.compareTo(defaultApprovedValue) > 0) return calculatedValue;
        else return defaultApprovedValue;
    }

    @Transactional
    public PayrollOutDto save(PayrollPostDto payrollDto) {

        if (!payrollDto.contract().agreeWithTerms()) {
            throw new PayrollException("É necessário concordar com os termos do contrato!");
        }


        PayrollLoanSimulatedDto payrollLoanSimulated = payrollLoanCalculate(
                payrollDto.totalValue(),
                payrollDto.installments());

        Contract contract = new Contract();

        contract.setExpectedPaymentCompletionDate(payrollDto.contract().expectedPaymentCompletionDate());
        contract.setTerms(payrollDto.contract().terms());
        contract.setBeginDate(LocalDate.now());
        contract.setAgreeWithTerms(true);

        contract = contractRepository.save(contract);

        Payroll payroll = new Payroll();
        Fee fee = feeRepository.findByActiveEquals(true);

        payroll.setAccountId(payrollDto.accountId());
        payroll.setInstallments(payrollDto.installments());
        payroll.setTotalValue(payrollLoanSimulated.totalValue());
        payroll.setAppliedFeeId(fee.getId());
        payroll.setStatus(PayrollStatus.ANALYSIS);
        payroll.setPaidInstallments(0);
        payroll.setValuePerMonth(payrollLoanSimulated.installmentValue());
        payroll.setContractId(contract.getId());

        payroll = payrollRepository.save(payroll);
        //TODO currentAccountClient.updateBalance(payroll.getAccountId(), payroll.getTotalValue());

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("save")
                .setContext("main")
                .setLevel(LogLevel.INFO)
                .setMessage("save payroll: " + payroll.getId())
                .setIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRemoteAddr())
                .build());

        return payrollMapper.entityToOutDto(payroll);

    }


    public PayrollOutDto update(PayrollPutDto payrollDto) {
        Payroll payrollSaved = payrollRepository.findById(payrollDto.id())
                .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado."));

        Payroll payroll = payrollMapper.putDtoToEntity(payrollDto);
        payroll.setId(payrollSaved.getId());
        payrollRepository.save(payroll);

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("update")
                .setContext("main")
                .setLevel(LogLevel.INFO)
                .setMessage("payroll: " + payroll.getId())
                .setIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRemoteAddr())
                .build());

        return payrollMapper.entityToOutDto(payroll);
    }

    public List<RequirementDetailDto> findPayrolls(Integer accountId) {
        List<Payroll> payrolls = payrollRepository.findAllByAccountId(accountId);

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("findPayrolls")
                .setContext("main")
                .setLevel(LogLevel.INFO)
                .setMessage("find all payrolls with accountId: " + accountId)
                .setIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRemoteAddr())
                .build());

        return payrolls.stream()
                .map(payrollMapper::entityToRequirementDetail)
                .toList();
    }

    public boolean paymentDay() {
        List<Payroll> payrolls = payrollRepository.findAll();

        for (Payroll payroll : payrolls) {
            if (payroll.getInstallments() < payroll.getPaidInstallments()) {
                //TODO paymentClient.payMonthlyInstallment();
            }
        }

        return true;
    }

    public List<PayrollOutDto> findAll() {
        List<Payroll> payrolls = payrollRepository.findAll();

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("findAll")
                .setContext("main")
                .setLevel(LogLevel.INFO)
                .setMessage("find all payrolls")
                .setIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRemoteAddr())
                .build());

        return payrolls.stream()
                .map(payrollMapper::entityToOutDto)
                .toList();
    }
}
