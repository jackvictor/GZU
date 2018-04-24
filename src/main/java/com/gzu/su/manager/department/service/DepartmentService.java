package com.gzu.su.manager.department.service;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.goods.model.GoodsInfo;

/**
 * @author: User
 * @create: 2018/04/21 15:26
 * @description: 部门逻辑层接口
 **/
public interface DepartmentService {
    PageResult<DepartmentInfo> findByPage(Integer startNum, Integer size, String dName) throws Exception;

    int deleteByPrimaryKey(String id) throws Exception;

    int insert(DepartmentInfo departmentInfo) throws Exception;

    int insertParam(DepartmentInfo departmentInfo) throws Exception;

    int updateByPrimaryKey(DepartmentInfo departmentInfo) throws Exception;

    DepartmentInfo findDepartmentById(String id) throws Exception;
}
