package com.gzu.su.manager.user.service.impl;


import com.gzu.su.manager.common.utils.EmailConfig;
import com.gzu.su.manager.common.utils.Md5Utils;
import com.gzu.su.manager.user.dao.UserInfoMapper;
import com.gzu.su.manager.user.model.UserInfo;
import com.gzu.su.manager.user.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service(value = "emailService")
public class EmailServiceImpl implements EmailService {
	@Autowired
	private EmailConfig emailConfig;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int sendSimpleMail(String sendTo,String userId) throws Exception{
		UserInfo userInfo = userInfoMapper.loginCheckUserName(userId);
		if(null == userInfo){
			return 0;
		}
		
		int pwd = (int) ((Math.random()*9+1)*100000);
		String salt = UUID.randomUUID().toString().replace("-","");
		String password = Md5Utils.GetMD5Code(String.valueOf(pwd) + salt);
		userInfo.setRandomSalt(salt);
		userInfo.setEncryptPassword(password);
		userInfoMapper.updateByPrimaryKey(userInfo);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfig.getEmailFrom());
		message.setTo(userInfo.geteMail());
		message.setSubject("重置密码");
		message.setText("密码已重置为："+String.valueOf(pwd));
		mailSender.send(message);
		return 200;
	}

}
