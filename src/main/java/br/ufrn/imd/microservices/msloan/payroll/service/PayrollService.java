package br.ufrn.imd.microservices.msloan.payroll.service;

import br.ufrn.imd.microservices.msloan.core.client.CurrentAccountClient;
import br.ufrn.imd.microservices.msloan.core.exceptions.NotFoundException;
import br.ufrn.imd.microservices.msloan.core.exceptions.PayrollException;
import br.ufrn.imd.microservices.msloan.feesetting.model.Fee;
import br.ufrn.imd.microservices.msloan.feesetting.repository.FeeRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    private final FeeRepository feeRepository;
    private final ContractRepository contractRepository;
    private final PayrollRepository payrollRepository;
    private final PayrollMapper payrollMapper;
    private final CurrentAccountClient currentAccountClient;

    public PayrollLoanSimulatedDto simulatePayrollLoan(PayrollLoanSimulationDto simulation) {
        BigDecimal approved = approvedValueByAccount(simulation.accountId());

        if (simulation.totalValue().compareTo(approved) > 0) {
            throw new PayrollException("Usuário não pode solicitar empréstime para este valor.");
        }

        return payrollLoanCalculate(simulation.totalValue(), simulation.installments());

    }

    private PayrollLoanSimulatedDto payrollLoanCalculate(BigDecimal totalValue, int installments) {
        Fee fee = feeRepository.findByActiveEquals(true);

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
//        List<Check> checks = currentAccountClient.allChecksByAccount(accountId);
//        Check check = checks.stream()
//                .max((check1, check2) -> check1.limite().compareTo(check2.limite()))
//                .orElseThrow(NoSuchElementException::new);

//        TODO
//        return check.limite().multiply(BigDecimal.TEN);
        return BigDecimal.valueOf(Long.MAX_VALUE);
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

//        TODO
//        currentAccountClient.postRecurrence(payroll);

        return payrollMapper.entityToOutDto(payroll);

    }

    public PayrollService(FeeRepository feeRepository,
                          CurrentAccountClient currentAccountClient,
                          ContractRepository contractRepository,
                          PayrollRepository payrollRepository,
                          PayrollMapper payrollMapper) {
        this.feeRepository = feeRepository;
        this.currentAccountClient = currentAccountClient;
        this.contractRepository = contractRepository;
        this.payrollRepository = payrollRepository;
        this.payrollMapper = payrollMapper;
    }

    public PayrollOutDto update(PayrollPutDto payrollDto) {
        Payroll payrollSaved = payrollRepository.findById(payrollDto.id())
                .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado."));

        Payroll payroll = payrollMapper.putDtoToEntity(payrollDto);
        payroll.setId(payrollSaved.getId());
        payrollRepository.save(payroll);

//        TODO
//        currentAccountClient.postRecurrence(payroll);

        return payrollMapper.entityToOutDto(payroll);
    }

    public List<RequirementDetailDto> findPayrolls(Integer accountId) {
        List<Payroll> payrolls = payrollRepository.findAllByAccountId(accountId);
        return payrolls.stream()
                .map(payrollMapper::entityToRequirementDetail)
                .toList();
    }
}
