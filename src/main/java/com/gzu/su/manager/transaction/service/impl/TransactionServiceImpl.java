package com.gzu.su.manager.transaction.service.impl;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.transaction.dao.TransactionInfoMapper;
import com.gzu.su.manager.transaction.model.TransactionInfo;
import com.gzu.su.manager.transaction.model.vo.TransactionInfoVo;
import com.gzu.su.manager.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: jack.ye
 * @create: 2018/04/23 9:31
 * @description: 事务管理业务逻辑层实现
 **/
@Service(value = "TransactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionInfoMapper transactionInfoMapper;

    @Override
    public PageResult<TransactionInfoVo> findByPage(Integer startNum, Integer size, String tName) throws Exception {
        PageResult<TransactionInfoVo> result = new PageResult<TransactionInfoVo>();
        result.setData(transactionInfoMapper.findByPage(startNum,size,tName));
        result.setCount(transactionInfoMapper.findByPageCount(tName));
        return result;
    }

    @Override
    public int deleteByPrimaryKey(String id) throws Exception {
       return transactionInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TransactionInfo transactionInfo) throws Exception {
       return transactionInfoMapper.insert(transactionInfo);
    }

    @Override
    public int updateByPrimaryKey(TransactionInfo transactionInfo) throws Exception {
        return 0;
    }

    @Override
    public TransactionInfo findTransactionById(String id) throws Exception {
        return transactionInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int findByName(String tName) throws Exception {
        return 0;
    }
}
