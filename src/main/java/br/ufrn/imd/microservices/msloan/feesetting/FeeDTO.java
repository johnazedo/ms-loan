package br.ufrn.imd.microservices.msloan.feesetting;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FeeDTO(String id,
                     @NotNull
                     BigDecimal percentage,
                     LocalDate createdAt,
                     boolean active) {
}
