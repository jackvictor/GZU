package com.gzu.su.manager.transaction.model;

import java.util.Date;

public class DepartmentInfo {
    private String id;

    private String depName;

    private String depMinsterName;

    private String depMemberSum;

    private String ministerPhoneNu;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepMinsterName() {
        return depMinsterName;
    }

    public void setDepMinsterName(String depMinsterName) {
        this.depMinsterName = depMinsterName;
    }

    public String getDepMemberSum() {
        return depMemberSum;
    }

    public void setDepMemberSum(String depMemberSum) {
        this.depMemberSum = depMemberSum;
    }

    public String getMinisterPhoneNu() {
        return ministerPhoneNu;
    }

    public void setMinisterPhoneNu(String ministerPhoneNu) {
        this.ministerPhoneNu = ministerPhoneNu;
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
}
