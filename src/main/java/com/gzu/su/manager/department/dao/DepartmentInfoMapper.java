package com.gzu.su.manager.department.dao;

import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.goods.model.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(DepartmentInfo record);

    int insertSelective(DepartmentInfo record);

    DepartmentInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DepartmentInfo record);

    int updateByPrimaryKey(DepartmentInfo record);

    List<DepartmentInfo> findByPage(@Param("startNum") Integer startNum, @Param("size") Integer size, @Param("dName") String dName) throws Exception;

    Integer findByPageCount(@Param("dName") String dName) throws Exception;

    List<DepartmentInfo> findAllDepartment() throws Exception;
}