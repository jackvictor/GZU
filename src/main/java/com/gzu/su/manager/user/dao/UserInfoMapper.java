package com.gzu.su.manager.user.dao;

import com.gzu.su.manager.user.model.UserInfo;
import com.gzu.su.manager.user.model.vo.UserPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserPage> findByPage(@Param("startNum")Integer startNum, @Param("size")Integer size, @Param("uName")String uName, @Param("sysUser")String sysuser, @Param("uId")String uId) throws Exception;

    /**
     *
     * @param uName 用户名
     * @return 总数
     * @throws Exception
     * @returnType Integer
     * @author luoxia
     * @date Mar 30, 201810:15:52 AM
     * @Des 获取页面总条数
     */
    Integer findByPageCount(@Param("uName")String uName,@Param("sysUser")String sysuser,@Param("uId")String uId) throws Exception;

    /**
     * @param userName 用户名
     * @throws Exception
     * @returnType Integer
     * @author luoxia
     * @date Mar 30, 201810:16:42 AM
     * @Des 验证用户名
     */
    Integer checkUserName(@Param("userId")String userName) throws Exception;

    /**
     *
     * @param userName 用户名
     * @return 用户信息
     * @throws Exception
     * @returnType UserInfo
     * @author luoxia
     * @date Mar 30, 201810:17:43 AM
     * @Des 登陆时验证用户名
     */
    UserInfo loginCheckUserName(@Param("userId")String userName) throws Exception;
}