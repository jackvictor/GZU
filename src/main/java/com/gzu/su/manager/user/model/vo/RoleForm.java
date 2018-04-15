package com.gzu.su.manager.user.model.vo;

import java.util.Date;
import java.util.List;

/**
 * @author: jack
 * @create: 2018/04/12 9:17
 * @description: 角色页面显示
 **/
public class RoleForm {
    //记录ID
    private String id;

    //角色名字
    private String roleName;

    //角色描述
    private String roleDesc;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //角色状态
    private String roleStatus;
    //权限勾选状态
    private List<String> checkedFuns;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public List<String> getCheckedFuns() {
        return checkedFuns;
    }

    public void setCheckedFuns(List<String> checkedFuns) {
        this.checkedFuns = checkedFuns;
    }
}
