package com.gzu.su.manager.finance.model;

import java.util.Date;

public class FinanceInfo {
    private String id;

    private String financeSource;

    private String financePropose;

    private String financeMoney;

    private String financeDepartment;

    private String financeRemain;

    private Date createTime;

    private Date updateTime;

    private String financeAdvice;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFinanceSource() {
        return financeSource;
    }

    public void setFinanceSource(String financeSource) {
        this.financeSource = financeSource == null ? null : financeSource.trim();
    }

    public String getFinancePropose() {
        return financePropose;
    }

    public void setFinancePropose(String financePropose) {
        this.financePropose = financePropose == null ? null : financePropose.trim();
    }

    public String getFinanceMoney() {
        return financeMoney;
    }

    public void setFinanceMoney(String financeMoney) {
        this.financeMoney = financeMoney == null ? null : financeMoney.trim();
    }

    public String getFinanceDepartment() {
        return financeDepartment;
    }

    public void setFinanceDepartment(String financeDepartment) {
        this.financeDepartment = financeDepartment == null ? null : financeDepartment.trim();
    }

    public String getFinanceRemain() {
        return financeRemain;
    }

    public void setFinanceRemain(String financeRemain) {
        this.financeRemain = financeRemain == null ? null : financeRemain.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFinanceAdvice() {
        return financeAdvice;
    }

    public void setFinanceAdvice(String financeAdvice) {
        this.financeAdvice = financeAdvice == null ? null : financeAdvice.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}