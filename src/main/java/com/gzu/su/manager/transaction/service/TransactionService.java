package com.gzu.su.manager.transaction.service;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.transaction.model.TransactionInfo;
import com.gzu.su.manager.transaction.model.vo.TransactionInfoVo;

/**
 * @author: User
 * @create: 2018/04/21 14:41
 * @description:事务管理接口
 **/
public interface TransactionService {
    PageResult<TransactionInfoVo> findByPage(Integer startNum, Integer size, String dName) throws Exception;

    int deleteByPrimaryKey(String id) throws Exception;

    int insert(TransactionInfo transactionInfo) throws Exception;

    int updateByPrimaryKey(TransactionInfo transactionInfo) throws Exception;

    TransactionInfo findTransactionById(String id) throws Exception;

    int findByName(String tName) throws Exception;
}
