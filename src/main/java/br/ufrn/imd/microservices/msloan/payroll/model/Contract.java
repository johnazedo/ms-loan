package br.ufrn.imd.microservices.msloan.payroll.model;

import java.time.LocalDate;
import java.util.UUID;

public class Contract {

    private UUID id;

    private String terms;
    private boolean agreeWithTerms;
    private LocalDate beginDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public boolean isAgreeWithTerms() {
        return agreeWithTerms;
    }

    public void setAgreeWithTerms(boolean agreeWithTerms) {
        this.agreeWithTerms = agreeWithTerms;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }
}
