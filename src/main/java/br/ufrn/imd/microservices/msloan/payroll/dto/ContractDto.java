package br.ufrn.imd.microservices.msloan.payroll.dto;

import java.time.LocalDate;

public record ContractDto(String id,
                          String terms,
                          boolean agreeWithTerms,
                          LocalDate beginDate,
                          LocalDate expectedPaymentCompletionDate) {
}
