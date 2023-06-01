package br.ufrn.imd.microservices.msloan.feesetting.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Fee {

    private UUID id;
    private BigDecimal percentage;

    private LocalDate createdAt;
    
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
