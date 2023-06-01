package br.ufrn.imd.microservices.msloan.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Payroll {

    private UUID id;
    private String userId;
    private Integer installments;
    private BigDecimal totalValue;
    private UUID appliedFee;
    private PayrollStatus status;
    private Integer paidInstallments;
    private BigDecimal valuePerMonth;
    private LocalDate contractDate;
    private LocalDate expectedPaymentCompletionDate;
    private Contract contract;

    public PayrollStatus getStatus() {
        return status;
    }

    public void setStatus(PayrollStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public UUID getAppliedFee() {
        return appliedFee;
    }

    public void setAppliedFee(UUID appliedFee) {
        this.appliedFee = appliedFee;
    }

    public Integer getPaidInstallments() {
        return paidInstallments;
    }

    public void setPaidInstallments(Integer paidInstallments) {
        this.paidInstallments = paidInstallments;
    }

    public BigDecimal getValuePerMonth() {
        return valuePerMonth;
    }

    public void setValuePerMonth(BigDecimal valuePerMonth) {
        this.valuePerMonth = valuePerMonth;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public LocalDate getExpectedPaymentCompletionDate() {
        return expectedPaymentCompletionDate;
    }

    public void setExpectedPaymentCompletionDate(LocalDate expectedPaymentCompletionDate) {
        this.expectedPaymentCompletionDate = expectedPaymentCompletionDate;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
