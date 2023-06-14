package br.ufrn.imd.microservices.msloan.payroll.dto;

import br.ufrn.imd.microservices.msloan.payroll.model.PayrollStatus;

import java.math.BigDecimal;

public record PayrollPostDto(String id,
                             Integer accountId,
                             Integer installments,
                             BigDecimal totalValue,
                             String appliedFee,
                             PayrollStatus status,
                             Integer paidInstallments,
                             BigDecimal valuePerMonth,
                             ContractDto contract) {
}
