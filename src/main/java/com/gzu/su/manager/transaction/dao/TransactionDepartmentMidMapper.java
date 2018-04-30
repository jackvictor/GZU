package com.gzu.su.manager.transaction.dao;

import com.gzu.su.manager.transaction.model.TransactionDepartmentMid;

public interface TransactionDepartmentMidMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransactionDepartmentMid record);

    int insertSelective(TransactionDepartmentMid record);

    TransactionDepartmentMid selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransactionDepartmentMid record);

    int updateByPrimaryKey(TransactionDepartmentMid record);

    int deleteByTransactionId(String id);
}