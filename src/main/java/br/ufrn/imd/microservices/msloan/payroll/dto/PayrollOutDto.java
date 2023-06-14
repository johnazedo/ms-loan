package br.ufrn.imd.microservices.msloan.payroll.dto;

import br.ufrn.imd.microservices.msloan.payroll.model.PayrollStatus;

import java.math.BigDecimal;

public record PayrollOutDto(String id,
                            Integer accountId,
                            Integer installments,
                            BigDecimal totalValue,
                            String appliedFeeId,
                            PayrollStatus status,
                            Integer paidInstallments,
                            BigDecimal valuePerMonth,
                            String contractId) {
}
