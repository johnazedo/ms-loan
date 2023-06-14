package br.ufrn.imd.microservices.msloan.payroll.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "payrolls")
public class Payroll {

    private String id;
    private Integer accountId;
    private Integer installments;
    private BigDecimal totalValue;
    private String appliedFeeId;
    private PayrollStatus status;
    private Integer paidInstallments;
    private BigDecimal valuePerMonth;
    private String contractId;

    public PayrollStatus getStatus() {
        return status;
    }

    public void setStatus(PayrollStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public String getAppliedFeeId() {
        return appliedFeeId;
    }

    public void setAppliedFeeId(String appliedFeeId) {
        this.appliedFeeId = appliedFeeId;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contract) {
        this.contractId = contract;
    }
}
