package br.ufrn.imd.microservices.msloan.payroll.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "contracts")
public class Contract {

    private String id;
    private String terms;
    private boolean agreeWithTerms;
    private LocalDate beginDate;
    private LocalDate expectedPaymentCompletionDate;
    private LocalDate monthlyPayment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDate getExpectedPaymentCompletionDate() {
        return expectedPaymentCompletionDate;
    }

    public void setExpectedPaymentCompletionDate(LocalDate expectedPaymentCompletionDate) {
        this.expectedPaymentCompletionDate = expectedPaymentCompletionDate;
    }

    public LocalDate getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(LocalDate monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
