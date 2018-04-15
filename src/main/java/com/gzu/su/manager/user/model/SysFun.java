package com.gzu.su.manager.user.model;

import java.util.Date;

public class SysFun {
    private String id;

    private String funName;

    private String funIcon;

    private String yfunUrl;

    private String parentId;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }

    public String getFunIcon() {
        return funIcon;
    }

    public void setFunIcon(String funIcon) {
        this.funIcon = funIcon == null ? null : funIcon.trim();
    }

    public String getYfunUrl() {
        return yfunUrl;
    }

    public void setYfunUrl(String yfunUrl) {
        this.yfunUrl = yfunUrl == null ? null : yfunUrl.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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