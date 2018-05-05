package com.gzu.su.manager.transaction.model.vo;

import com.gzu.su.manager.department.model.DepartmentInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    private String transactionContent;

    private List<DepartmentInfo> departmentInfos;

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

    public String getTransactionFinish() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(transactionFinish);
    }

    public void setTransactionFinish(Date transactionFinish) {
        this.transactionFinish = transactionFinish;
    }

    public String getTransactionBegin() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(transactionBegin);
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

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public List<DepartmentInfo> getDepartmentInfos() {
        return departmentInfos;
    }

    public void setDepartmentInfos(List<DepartmentInfo> departmentInfos) {
        this.departmentInfos = departmentInfos;
    }
}
