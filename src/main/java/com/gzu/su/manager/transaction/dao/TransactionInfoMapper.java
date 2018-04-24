package com.gzu.su.manager.transaction.dao;

import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.transaction.model.TransactionInfo;
import com.gzu.su.manager.transaction.model.vo.TransactionInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransactionInfo record);

    int insertSelective(TransactionInfo record);

    TransactionInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransactionInfo record);

    int updateByPrimaryKey(TransactionInfo record);

    List<TransactionInfoVo> findByPage(@Param("startNum") Integer startNum, @Param("size") Integer size, @Param("dName") String dName) throws Exception;

    Integer findByPageCount(@Param("dName") String dName) throws Exception;

    Integer findByName(@Param("tName") String tName) throws Exception;
}