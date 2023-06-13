package br.ufrn.imd.microservices.msloan.payroll;

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
