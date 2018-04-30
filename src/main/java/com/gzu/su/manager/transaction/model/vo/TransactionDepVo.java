package com.gzu.su.manager.transaction.model.vo;

import java.util.Date;
import java.util.List;

/**
 * @author: jack.ye
 * @create: 2018/04/25 18:20
 * @description: 更改和新增的页面model
 **/
public class TransactionDepVo {
    //事务ID
    private String id;

    private String transactionName;

    private String statusDesc;

    private String transactionScheme;

    private String transactionDate;

    private Date createTime;

    private Date updateTime;

    private String status;

    private String transactionContent;
    //部门ID集合
    private List<String> depIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getTransactionScheme() {
        return transactionScheme;
    }

    public void setTransactionScheme(String transactionScheme) {
        this.transactionScheme = transactionScheme;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
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
        this.status = status;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public List<String> getDepIds() {
        return depIds;
    }

    public void setDepIds(List<String> depIds) {
        this.depIds = depIds;
    }
}
