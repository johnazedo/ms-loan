package br.ufrn.imd.microservices.msloan.feesetting;

import java.math.BigDecimal;
import java.util.UUID;

public class Fee {

    private UUID id;
    private BigDecimal percentage;

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
