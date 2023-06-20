package br.ufrn.imd.microservices.msloan.feesetting.service;

import br.ufrn.imd.microservices.msloan.core.exceptions.NotFoundException;
import br.ufrn.imd.microservices.msloan.core.exceptions.InvalidFeeException;
import br.ufrn.imd.microservices.msloan.core.log.Log;
import br.ufrn.imd.microservices.msloan.core.log.LogSender;
import br.ufrn.imd.microservices.msloan.feesetting.dto.FeeDto;
import br.ufrn.imd.microservices.msloan.feesetting.model.Fee;
import br.ufrn.imd.microservices.msloan.feesetting.repository.FeeCustomRepository;
import br.ufrn.imd.microservices.msloan.feesetting.repository.FeeRepository;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class FeeService {

    private final FeeRepository repository;
    private final FeeCustomRepository customRepository;
    private final LogSender logger;

    public FeeService(FeeRepository feeRepository, FeeCustomRepository customRepository, LogSender logger) {
        this.repository = feeRepository;
        this.customRepository = customRepository;
        this.logger = logger;
    }

    @Transactional
    public FeeDto save(FeeDto feeDTO) {
        if(feeDTO.percentage().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidFeeException("Fee less or equal to zero.");
        }

        Fee fee = new Fee();
        fee.setActive(true);
        fee.setPercentage(feeDTO.percentage());
        fee.setCreatedAt(LocalDateTime.now());

        customRepository.updateAllActive();
        Fee saved = repository.save(fee);

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("save")
                .setContext("main")
                .setLevel(LogLevel.INFO)
                .setMessage("Fee saved: " + saved.getId())
                .build());

        return new FeeDto(saved.getId(),
                saved.getPercentage(),
                saved.getCreatedAt(),
                saved.getActive());
    }

    public void update(FeeDto feeDTO) {
        Fee feeSaved = repository.findById(feeDTO.id())
                .orElseThrow(() -> new NotFoundException("Fee don't exists"));

        if(feeDTO.percentage().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidFeeException("Fee less or equal to zero.");
        }

        feeSaved.setPercentage(feeDTO.percentage());
        repository.save(feeSaved);

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("update")
                .setContext("main")
                .setLevel(LogLevel.INFO)
                .setMessage("Fee updated: " + feeSaved.getId())
                .build());
    }
}
