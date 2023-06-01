package br.ufrn.imd.microservices.msloan.requirementdetail.dto;

import br.ufrn.imd.microservices.msloan.payroll.model.PayrollStatus;

public record RequirementDetailDTO(PayrollStatus status,
                                   boolean valueAvailable) {


}
