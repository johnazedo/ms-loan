package br.ufrn.imd.microservices.msloan.simulation;

import java.math.BigDecimal;

public record PayrollLoanSimulatedDto(BigDecimal installmentValue,
                                      BigDecimal fee,
                                      BigDecimal totalValue,
                                      int installments) {
}
