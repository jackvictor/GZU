package com.gzu.su.manager.user.model.vo;

/**
 * @author: jack.ye
 * @create: 2018/04/12 9:16
 * @description: 用户密码
 **/
public class UserPwd {
    //记录ID
    private String id;

    //新密码
    private String npwd;

    //再次输入新密码
    private String snpwd;

    //密码
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNpwd() {
        return npwd;
    }

    public void setNpwd(String npwd) {
        this.npwd = npwd;
    }

    public String getSnpwd() {
        return snpwd;
    }

    public void setSnpwd(String snpwd) {
        this.snpwd = snpwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
