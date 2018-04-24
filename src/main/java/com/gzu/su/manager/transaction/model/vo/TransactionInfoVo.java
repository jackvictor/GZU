package com.gzu.su.manager.transaction.model.vo;

import com.gzu.su.manager.department.model.DepartmentInfo;

import java.util.Date;

/**
 * @author: jack.ye
 * @create: 2018/04/21 15:19
 * @description: 事务管理页面model
 **/
public class TransactionInfoVo {
    private String id;

    private String transactionName;

    private String statusDesc;

    private String transactionScheme;

    private Date transactionFinish;

    private Date transactionBegin;

    private Date createTime;

    private Date updateTime;

    private String status;

    private DepartmentInfo departmentList;

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
        this.status = status;
    }

    public DepartmentInfo getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(DepartmentInfo departmentList) {
        this.departmentList = departmentList;
    }
}
