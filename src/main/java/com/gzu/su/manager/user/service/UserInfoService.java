package com.gzu.su.manager.user.service;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.user.model.UserInfo;
import com.gzu.su.manager.user.model.vo.UserPage;
import com.gzu.su.manager.user.model.vo.UserPwd;

/**
 * @author: User
 * @create: 2018/04/12 8:48
 * @description: 用户信息服务层接口
 **/
public interface UserInfoService {

    /**
     * 根据指定页数查找用户详情
     *
     * @param startNum
     * @param size
     * @param uName
     * @param userInfo
     * @return
     * @throws Exception
     */
    PageResult<UserPage> findByPage(Integer startNum, Integer size, String uName, UserInfo userInfo) throws Exception;

    /**
     * 通过主键ID获取用户信息
     *
     * @param uId
     * @return
     * @throws Exception
     */
    UserInfo findUserByPid(String uId) throws Exception;

    /**
     * 插入用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    int insert(UserInfo user) throws Exception;

    /**
     * 更新用户信息
     * @param user
     * @return
     * @throws Exception
     */
    int updateUserInfo(UserInfo user) throws Exception;

    /**
     * 通过主键ID删除用户信息
     *
     * @param uId
     * @return
     * @throws Exception
     */
    int deleteUser(String uId) throws Exception;

    /**
     * 保存用户前检查用户是否同名
     *
     * @param userName
     * @return
     * @throws Exception
     */
    boolean saveCheckUserName(String userName) throws Exception;

    /**
     * 1:用户名不正确 2:密码不正确 3:用户停用;成功返回此条数据id
     *
     * @param userId
     * @param pwd
     * @return
     * @throws Exception
     */
    String login(String userId,String pwd) throws Exception;

    /**
     * 修改密码
     *
     * @param userPwd
     * @return
     * @throws Exception
     */
    int changePwd(UserPwd userPwd) throws Exception;

    /**
     * 根据用户对象重置密码
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    int resetPwd(UserInfo userInfo) throws Exception;
}
