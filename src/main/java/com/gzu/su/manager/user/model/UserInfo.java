package com.gzu.su.manager.user.model;

import java.util.Date;

public class UserInfo {
    //用户的id
    private String id;
    //用户名
    private String userId;
    //密码摘要
    private String encryptPassword;
    //随机盐
    private String randomSalt;
    //用户姓名
    private String userName;
    //用户角色id
    private String roleId;
    //用户性别
    private String userSex;
    //用户职位
    private String userPosition;
    //用户所在部门
    private String userDepartmentName;
    //用户所属专业
    private String userMajor;
    //用户所在年级
    private String userGrade;
    //用户邮箱
    private String eMail;
    //用户电话
    private String userPhoneNu;
    //用户入户时间
    private Date userStartTime;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //用户状态
    private String userStatus;
    //是否选中
    private boolean isCheck;
    //自己修改
    private String mine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword == null ? null : encryptPassword.trim();
    }

    public String getRandomSalt() {
        return randomSalt;
    }

    public void setRandomSalt(String randomSalt) {
        this.randomSalt = randomSalt == null ? null : randomSalt.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition == null ? null : userPosition.trim();
    }

    public String getUserDepartmentName() {
        return userDepartmentName;
    }

    public void setUserDepartmentName(String userDepartmentName) {
        this.userDepartmentName = userDepartmentName == null ? null : userDepartmentName.trim();
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor == null ? null : userMajor.trim();
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade == null ? null : userGrade.trim();
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail == null ? null : eMail.trim();
    }

    public String getUserPhoneNu() {
        return userPhoneNu;
    }

    public void setUserPhoneNu(String userPhoneNu) {
        this.userPhoneNu = userPhoneNu == null ? null : userPhoneNu.trim();
    }

    public Date getUserStartTime() {
        return userStartTime;
    }

    public void setUserStartTime(Date userStartTime) {
        this.userStartTime = userStartTime;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }
}