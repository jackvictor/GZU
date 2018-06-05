package com.gzu.su.manager.user.model.vo;

import com.gzu.su.manager.user.model.UserRoleMid;

import java.util.Date;

/**
 * @author: jack.ye
 * @create: 2018/04/12 9:56
 * @description: 用户信息页面显示
 **/
public class UserPage {
    private String uId;

    private String userId;

    private String userName;

    private String userSex;

    private String userPosition;

    private String userDepartmentName;

    private String userMajor;

    private String userGrade;

    private String eMail;

    private String userPhoneNu;

    private Date userStartTime;

    private String userStatus;

    private String roleName;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserDepartmentName() {
        return userDepartmentName;
    }

    public void setUserDepartmentName(String userDepartmentName) {
        this.userDepartmentName = userDepartmentName;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserPhoneNu() {
        return userPhoneNu;
    }

    public void setUserPhoneNu(String userPhoneNu) {
        this.userPhoneNu = userPhoneNu;
    }

    public Date getUserStartTime() {
        return userStartTime;
    }

    public void setUserStartTime(Date userStartTime) {
        this.userStartTime = userStartTime;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
