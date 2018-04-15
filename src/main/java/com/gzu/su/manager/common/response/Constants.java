package com.gzu.su.manager.common.response;

/**
 * 
 * @Class Constants    
 * @author mli8  
 * @date Mar 21, 20182:59:04 PM  
 * @Des 
 * @since JDK 1.8
 */
public class Constants {

    /**************** 错误码 ***************/
    public static final Integer SUCESS_CODE = 200;
    public static  final Integer ERROR_CODE_SYSTEM = 500; // 系统错误

    public static final Integer ERROR_CODE_VALIDATION = 403; // 后台验证不通过


    /****************用户角色 ***************/
    public static final String USER_ROLE_ADMIN="0"; // 管理员

    public static final String USER_ROLE_NORMAL = "1"; // 普通用户
    
    public static final String SYS_FUN_ID = "1001"; //系统主ID
    public static final String SYS_FUN_INDEX = "1002"; //首页ID
    public static final String SYS_USER_ADMIN = "admin"; //系统管理员
    
    public static final String RESET_PASSWORD = "111111"; //重置密码

    public static final String DEF_ENCODE = "UTF-8";
}
