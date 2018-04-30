package com.gzu.su.manager.transaction.model;

import java.util.Date;

public class TransactionInfo {
    private String id;

    private String transactionName;

    private String statusDesc;

    private String transactionScheme;

    private Date transactionFinish;

    private Date transactionBegin;

    private Date createTime;

    private Date updateTime;

    private String status;

    private String transactionContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName == null ? null : transactionName.trim();
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc == null ? null : statusDesc.trim();
    }

    public String getTransactionScheme() {
        return transactionScheme;
    }

    public void setTransactionScheme(String transactionScheme) {
        this.transactionScheme = transactionScheme == null ? null : transactionScheme.trim();
    }

    public Date getTransactionFinish() {
        return transactionFinish;
    }

    public void setTransactionFinish(Date transactionFinish) {
        this.transactionFinish = transactionFinish;
    }

    public Date getTransactionBegin() {
        return transactionBegin;
    }

    public void setTransactionBegin(Date transactionBegin) {
        this.transactionBegin = transactionBegin;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent == null ? null : transactionContent.trim();
    }
}