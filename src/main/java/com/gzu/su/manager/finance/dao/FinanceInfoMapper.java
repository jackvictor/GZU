package com.gzu.su.manager.finance.dao;

import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.finance.model.FinanceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinanceInfo record);

    int insertSelective(FinanceInfo record);

    FinanceInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinanceInfo record);

    int updateByPrimaryKey(FinanceInfo record);

    List<FinanceInfo> findByPage(@Param("startNum") Integer startNum, @Param("size") Integer size, @Param("fName") String fName) throws Exception;

    Integer findByPageCount(@Param("fName") String fName) throws Exception;

    List<FinanceInfo> checkByPage(@Param("startNum") Integer startNum, @Param("size") Integer size, @Param("fName") String fName) throws Exception;

    Integer checkByPageCount(@Param("fName") String fName) throws Exception;
}
