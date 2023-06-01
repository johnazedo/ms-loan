package br.ufrn.imd.microservices.msloan.payroll.dto;

import br.ufrn.imd.microservices.msloan.payroll.model.Contract;
import br.ufrn.imd.microservices.msloan.payroll.model.PayrollStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PayrollDTO(String userId,
                         Integer installments,
                         BigDecimal totalValue,
                         UUID appliedFee,
                         PayrollStatus status,
                         Integer paidInstallments,
                         BigDecimal valuePerMonth,
                         LocalDate contractDate,
                         LocalDate expectedPaymentCompletionDate,
                         Contract contract) {
}
