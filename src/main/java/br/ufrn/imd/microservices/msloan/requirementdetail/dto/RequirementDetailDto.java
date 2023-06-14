package br.ufrn.imd.microservices.msloan.requirementdetail.dto;

import br.ufrn.imd.microservices.msloan.payroll.model.PayrollStatus;

import java.math.BigDecimal;

public record RequirementDetailDto(String id,
                                   PayrollStatus status,
                                   BigDecimal totalValue) {


}
