package br.ufrn.imd.microservices.msloan.feesetting;

import br.ufrn.imd.microservices.msloan.core.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class FeeService {

    private final FeeRepository repository;
    private final FeeCustomRepository customRepository;

    public FeeService(FeeRepository feeRepository, FeeCustomRepository customRepository) {
        this.repository = feeRepository;
        this.customRepository = customRepository;
    }

    @Transactional
    public FeeDTO save(FeeDTO feeDTO) {
        if(feeDTO.percentage().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidFeeException("Fee less or equal to zero.");
        }

        Fee fee = new Fee();
        fee.setActive(true);
        fee.setPercentage(feeDTO.percentage());
        fee.setCreatedAt(LocalDate.now());

        customRepository.updateAllActive();
        Fee saved = repository.save(fee);

        return new FeeDTO(saved.getId(),
                saved.getPercentage(),
                saved.getCreatedAt(),
                saved.getActive());
    }

    public void update(FeeDTO feeDTO) {
        Fee feeSaved = repository.findById(feeDTO.id())
                .orElseThrow(() -> new NotFoundException("Fee don't exists"));

        if(feeDTO.percentage().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidFeeException("Fee less or equal to zero.");
        }

        feeSaved.setPercentage(feeDTO.percentage());
        repository.save(feeSaved);
    }
}
