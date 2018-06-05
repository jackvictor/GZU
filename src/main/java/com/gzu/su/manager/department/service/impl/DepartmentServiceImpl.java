package com.gzu.su.manager.department.service.impl;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.dao.DepartmentInfoMapper;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.department.service.DepartmentService;
import com.gzu.su.manager.goods.model.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: jack.ye
 * @create: 2018/04/21 15:27
 * @description:
 **/
@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentInfoMapper departmentInfoMapper;
    @Override
    public PageResult<DepartmentInfo> findByPage(Integer startNum, Integer size, String dName) throws Exception {
        PageResult<DepartmentInfo> result = new PageResult<DepartmentInfo>();
        result.setData(departmentInfoMapper.findByPage(startNum,size,dName));
        result.setCount(departmentInfoMapper.findByPageCount(dName));
        return result;
    }

    @Override
    public int deleteByPrimaryKey(String id) throws Exception {
        return departmentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DepartmentInfo departmentInfo) throws Exception {
        if(!StringUtils.isEmpty(departmentInfo.getId())){
            DepartmentInfo departmentInfo1 = departmentInfoMapper.selectByPrimaryKey(departmentInfo.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(departmentInfo1.getCreateTime());
            departmentInfo.setCreateTime(date);
            departmentInfo.setUpdateTime(new Date());
            return departmentInfoMapper.updateByPrimaryKeySelective(departmentInfo);
        }else {
            String id =  UUID.randomUUID().toString().replace("-","");
            departmentInfo.setId(id);
            departmentInfo.setCreateTime(new Date());
            departmentInfo.setUpdateTime(new Date());
            return departmentInfoMapper.insert(departmentInfo);
        }
    }

    @Override
    public int insertParam(DepartmentInfo departmentInfo) throws Exception {
        return departmentInfoMapper.insert(departmentInfo);
    }

    @Override
    public int updateByPrimaryKey(DepartmentInfo departmentInfo) throws Exception {
        return departmentInfoMapper.updateByPrimaryKey(departmentInfo);
    }

    @Override
    public DepartmentInfo findDepartmentById(String id) throws Exception {
        return departmentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DepartmentInfo> findAllDepartment() throws Exception {
        return departmentInfoMapper.findAllDepartment("");
    }
}
