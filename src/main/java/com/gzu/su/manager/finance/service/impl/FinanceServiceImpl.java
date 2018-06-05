package com.gzu.su.manager.finance.service.impl;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.finance.dao.FinanceInfoMapper;
import com.gzu.su.manager.finance.model.FinanceInfo;
import com.gzu.su.manager.finance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service(value = "financeService")
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    FinanceInfoMapper financeInfoMapper;

    @Override
    public PageResult<FinanceInfo> findByPage(Integer startNum, Integer size, String fName) throws Exception {
        PageResult<FinanceInfo> result = new PageResult<FinanceInfo>();
        result.setData(financeInfoMapper.findByPage(startNum,size,fName));
        result.setCount(financeInfoMapper.findByPageCount(fName));
        return result;
    }

    @Override
    public PageResult<FinanceInfo> checkByPage(Integer startNum, Integer size, String fName) throws Exception {
        PageResult<FinanceInfo> result = new PageResult<FinanceInfo>();
        result.setData(financeInfoMapper.checkByPage(startNum,size,fName));
        result.setCount(financeInfoMapper.checkByPageCount(fName));
        return result;
    }

    @Override
    public int deleteByPrimaryKey(String id) throws Exception {
        return financeInfoMapper.deleteByPrimaryKey(id) ;
    }

    @Override
    public int insert(FinanceInfo financeInfo) throws Exception {
        financeInfo.setCreateTime(new Date());
        financeInfo.setUpdateTime(new Date());
        return financeInfoMapper.insert(financeInfo);
    }

    @Override
    public int updateByPrimaryKey(FinanceInfo financeInfo) throws Exception {
        financeInfo.setUpdateTime(new Date());
        return financeInfoMapper.updateByPrimaryKey(financeInfo);
    }

    @Override
    public FinanceInfo findFinanceById(String id) throws Exception {
        return financeInfoMapper.selectByPrimaryKey(id);
    }
}
