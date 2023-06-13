package br.ufrn.imd.microservices.msloan.simulation;

import java.math.BigDecimal;
import java.util.UUID;

public record SimulationDTO(UUID userId,
                            BigDecimal totalValue,
                            int installments) {
}
