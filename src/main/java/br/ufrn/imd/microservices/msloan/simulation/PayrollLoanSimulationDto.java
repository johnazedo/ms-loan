package br.ufrn.imd.microservices.msloan.simulation;

import java.math.BigDecimal;

public record PayrollLoanSimulationDto(Integer accountId,
                                       BigDecimal totalValue,
                                       int installments) {
}
