package br.ufrn.imd.microservices.msloan.feesetting.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FeeDto(String id,
                     @NotNull
                     BigDecimal percentage,
                     LocalDateTime createdAt,
                     boolean active) {
}
