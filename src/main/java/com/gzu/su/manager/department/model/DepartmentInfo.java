package com.gzu.su.manager.department.model;

import java.text.SimpleDateFormat;
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
        this.id = id == null ? null : id.trim();
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    public String getDepMinsterName() {
        return depMinsterName;
    }

    public void setDepMinsterName(String depMinsterName) {
        this.depMinsterName = depMinsterName == null ? null : depMinsterName.trim();
    }

    public String getDepMemberSum() {
        return depMemberSum;
    }

    public void setDepMemberSum(String depMemberSum) {
        this.depMemberSum = depMemberSum == null ? null : depMemberSum.trim();
    }

    public String getMinisterPhoneNu() {
        return ministerPhoneNu;
    }

    public void setMinisterPhoneNu(String ministerPhoneNu) {
        this.ministerPhoneNu = ministerPhoneNu == null ? null : ministerPhoneNu.trim();
    }

    public String getCreateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}