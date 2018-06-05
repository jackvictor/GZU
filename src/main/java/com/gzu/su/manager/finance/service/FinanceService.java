package com.gzu.su.manager.finance.service;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.finance.model.FinanceInfo;

public interface FinanceService {
    PageResult<FinanceInfo > findByPage(Integer startNum, Integer size, String fName) throws Exception;

    PageResult<FinanceInfo > checkByPage(Integer startNum, Integer size, String fName) throws Exception;

    int deleteByPrimaryKey(String id) throws Exception;

    int insert(FinanceInfo financeInfo) throws Exception;

    int updateByPrimaryKey(FinanceInfo financeInfo) throws Exception;

    FinanceInfo findFinanceById(String id) throws Exception;
}
