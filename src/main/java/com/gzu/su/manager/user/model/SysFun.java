package com.gzu.su.manager.user.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @Class SysFun
 * @author mli8
 * @date Mar 20, 20181:25:29 PM
 * @Des 系统功能
 * @since JDK 1.8
 */
public class SysFun {
    //记录ID
    private String id;

    //功能名称
    private String funName;

    //功能图标
    private String funIcon;

    //功能页面地址
    private String funUrl;

    //父节点ID
    private String parentId;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //是否选中
    private boolean isCheck;

    private List<SysFun> sysFuns;

    public List<SysFun> getSysFuns() {
        return sysFuns;
    }

    public void setSysFuns(List<SysFun> sysFuns) {
        this.sysFuns = sysFuns;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getFunIcon() {
        return funIcon;
    }

    public void setFunIcon(String funIcon) {
        this.funIcon = funIcon;
    }

    public String getFunUrl() {
        return funUrl;
    }

    public void setFunUrl(String funUrl) {
        this.funUrl = funUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
