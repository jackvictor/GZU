package com.gzu.su.manager.user.service.impl;

import com.gzu.su.manager.common.response.Constants;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.common.utils.EmailConfig;
import com.gzu.su.manager.common.utils.Md5Utils;
import com.gzu.su.manager.user.dao.UserInfoMapper;
import com.gzu.su.manager.user.model.UserInfo;
import com.gzu.su.manager.user.model.vo.UserPage;
import com.gzu.su.manager.user.model.vo.UserPwd;
import com.gzu.su.manager.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author: jack.ye
 * @create: 2018/04/12 9:29
 * @description: 用户信息服务层实现
 **/
@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    UserInfoMapper  userInfoMapper;

    @Autowired
    EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public PageResult<UserPage> findByPage(Integer startNum, Integer size, String uName, UserInfo userInfo) throws Exception {
        PageResult<UserPage> result = new PageResult<UserPage>();
        result.setData(userInfoMapper.findByPage(startNum,size,uName, Constants.SYS_USER_ADMIN,userInfo.getId()));
        result.setCount(userInfoMapper.findByPageCount(uName,Constants.SYS_USER_ADMIN,userInfo.getId()));
        return result;
    }

    @Override
    public UserInfo findUserByPid(String uId) throws Exception {
       UserInfo user = userInfoMapper.selectByPrimaryKey(uId);
       return user;
    }

    @Override
    public int insert(UserInfo user) throws Exception {
        String salt =  UUID.randomUUID().toString().replace("-","");
        user.setId(salt);
        user.setEncryptPassword(Md5Utils.GetMD5Code(user.getEncryptPassword() + salt));
        user.setRandomSalt(salt);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userInfoMapper.insert(user);
    }

    @Override
    public int updateUserInfo(UserInfo user) throws Exception {
        if(!StringUtils.isEmpty(user.getId())){
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(user.getId());

            user.setCreateTime(userInfo.getCreateTime());
            user.setRandomSalt(userInfo.getRandomSalt());
            user.setEncryptPassword(userInfo.getEncryptPassword());
            user.setUpdateTime(new Date());

            userInfoMapper.updateByPrimaryKey(user);
        }

        return 0;
    }

    @Override
    public int deleteUser(String uId) throws Exception {
        return userInfoMapper.deleteByPrimaryKey(uId);
    }

    @Override
    public boolean saveCheckUserName(String userName) throws Exception {
        Integer count = userInfoMapper.checkUserName(userName);
        if(count > 0){
            return false;
        }
        return true;
    }

    @Override
    public String login(String userId,String pwd) throws Exception {
        UserInfo userInfo = userInfoMapper.loginCheckUserName(userId);
        if(null == userInfo){
            return "1";
        }
        if("1".equals(userInfo.getUserStatus())){
            return "3";
        }
        String password = Md5Utils.GetMD5Code(pwd + userInfo.getRandomSalt());
        if(!password.equals(userInfo.getEncryptPassword())){
            return "2";
        }
        return userInfo.getId();
    }

    @Override
    public int changePwd(UserPwd userPwd) throws Exception {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userPwd.getId());
        String password = Md5Utils.GetMD5Code(userPwd.getPwd() + userInfo.getRandomSalt());
        if(!password.equals(userInfo.getEncryptPassword())){
            return 1;
        }
        String salt = UUID.randomUUID().toString().replace("-","");
        userInfo.setEncryptPassword(Md5Utils.GetMD5Code(userPwd.getNpwd() + salt));
        userInfo.setRandomSalt(salt);
        userInfoMapper.updateByPrimaryKey(userInfo);

        return 2;
    }

    @Override
    public int resetPwd(UserInfo userInfo) throws Exception {
        String salt = UUID.randomUUID().toString().replace("-","");
        int pwd = (int) ((Math.random()*9+1)*100000);
        userInfo.setRandomSalt(salt);
        userInfo.setEncryptPassword(Md5Utils.GetMD5Code(String.valueOf(pwd) + salt));
        userInfoMapper.updateByPrimaryKey(userInfo);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(userInfo.geteMail());
        message.setSubject("重置密码");
        message.setText("密码已重置为："+String.valueOf(pwd));
        mailSender.send(message);
        return 0;
    }
}
