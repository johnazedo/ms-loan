package br.ufrn.imd.microservices.msloan.payroll.mapper;

import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollOutDto;
import br.ufrn.imd.microservices.msloan.payroll.dto.PayrollPutDto;
import br.ufrn.imd.microservices.msloan.payroll.model.Payroll;
import br.ufrn.imd.microservices.msloan.requirementdetail.dto.RequirementDetailDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PayrollMapper {

    PayrollOutDto entityToOutDto(Payroll payroll);
    Payroll putDtoToEntity(PayrollPutDto payrollPutDto);
    RequirementDetailDto entityToRequirementDetail(Payroll payroll);
}
