package com.gzu.su.manager.user.service;

/**
 * 
 * @author mli8  
 * @date Apr 12, 20184:22:28 PM  
 * @Des 邮箱接口
 * @since JDK 1.8
 */
public interface EmailService {
	/**
	 * 
	 * @param sendTo 邮箱
	 * @param userId 用户账号
	 * @author mli8  
	 * @date Apr 12, 20184:21:21 PM  
	 * @Des 发送邮件
	 */
	int sendSimpleMail(String sendTo, String userId) throws Exception;
}
