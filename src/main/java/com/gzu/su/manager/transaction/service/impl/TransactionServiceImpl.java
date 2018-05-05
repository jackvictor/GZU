package com.gzu.su.manager.transaction.service.impl;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.dao.DepartmentInfoMapper;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.transaction.dao.TransactionDepartmentMidMapper;
import com.gzu.su.manager.transaction.dao.TransactionInfoMapper;
import com.gzu.su.manager.transaction.model.TransactionDepartmentMid;
import com.gzu.su.manager.transaction.model.TransactionInfo;
import com.gzu.su.manager.transaction.model.vo.TransactionDepVo;
import com.gzu.su.manager.transaction.model.vo.TransactionInfoVo;
import com.gzu.su.manager.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: jack.ye
 * @create: 2018/04/23 9:31
 * @description: 事务管理业务逻辑层实现
 **/
@Service(value = "TransactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionInfoMapper transactionInfoMapper;

    @Autowired
    DepartmentInfoMapper departmentInfoMapper;

    @Autowired
    TransactionDepartmentMidMapper transactionDepartmentMidMapper;

    @Override
    public PageResult<TransactionInfoVo> findByPage(Integer startNum, Integer size, String tName) throws Exception {
        PageResult<TransactionInfoVo> result = new PageResult<TransactionInfoVo>();
        result.setData(transactionInfoMapper.findByPage(startNum,size,tName));
        result.setCount(transactionInfoMapper.findByPageCount(tName));
        return result;
    }

    @Override
    public PageResult<TransactionInfoVo> checkByPage(Integer startNum, Integer size, String tName) throws Exception {
        PageResult<TransactionInfoVo> result = new PageResult<TransactionInfoVo>();
        result.setData(transactionInfoMapper.checkByPage(startNum,size,tName));
        result.setCount(transactionInfoMapper.checkByPageCount(tName));
        return result;
    }

    @Override
    public int deleteByPrimaryKey(String pid) throws Exception {
        transactionInfoMapper.deleteByPrimaryKey(pid);
        transactionDepartmentMidMapper.deleteByTransactionId(pid);
        return 0;
    }


    @Override
    public int saveTransactionInfoVo(TransactionDepVo transactionDepVo) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TransactionInfo transactionInfo = new TransactionInfo();
        String pId = UUID.randomUUID().toString().replace("-","");
        transactionInfo.setId(pId);
        transactionInfo.setTransactionName(transactionDepVo.getTransactionName());
        transactionInfo.setTransactionContent(transactionDepVo.getTransactionContent());
        transactionInfo.setStatus(transactionDepVo.getStatus());
        transactionInfo.setStatusDesc(transactionDepVo.getStatusDesc());
        transactionInfo.setStatus("0");
        transactionInfo.setTransactionScheme(transactionDepVo.getTransactionScheme());
        transactionInfo.setCreateTime(new Date());
        transactionInfo.setUpdateTime(new Date());
        String[] date = transactionDepVo.getTransactionDate().split("~");
        transactionInfo.setTransactionBegin(format.parse(date[0].trim()));
        transactionInfo.setTransactionFinish(format.parse(date[1].trim()));
        transactionInfoMapper.insert(transactionInfo);
        if(null != transactionDepVo.getDepIds()){
            for(String depId : transactionDepVo.getDepIds()){
                TransactionDepartmentMid mid = new TransactionDepartmentMid();
                mid.setId(UUID.randomUUID().toString().replace("-",""));
                mid.setDepartmentId(depId);
                mid.setTransactionId(pId);
                mid.setCreateTime(new Date());
                mid.setUpdateTime(new Date());
                transactionDepartmentMidMapper.insert(mid);
            }
        }
        return 0;
    }

    @Override
    public int updateTransactionInfoVo(TransactionDepVo transactionDepVo) throws Exception {
        if(!StringUtils.isEmpty(transactionDepVo.getId())){
            transactionDepartmentMidMapper.deleteByPrimaryKey(transactionDepVo.getId());
            TransactionInfo  transactionInfo = transactionInfoMapper.selectByPrimaryKey(transactionDepVo.getId());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TransactionInfo setTransactionInfo = new TransactionInfo();
            String[] date = transactionDepVo.getTransactionDate().split("~");
            setTransactionInfo.setTransactionBegin(format.parse(date[0].trim()));
            setTransactionInfo.setTransactionFinish(format.parse(date[1].trim()));
            setTransactionInfo.setId(transactionInfo.getId());
            setTransactionInfo.setCreateTime(transactionInfo.getCreateTime());
            setTransactionInfo.setUpdateTime(new Date());
            setTransactionInfo.setTransactionName(transactionDepVo.getTransactionName());
            setTransactionInfo.setStatusDesc(transactionDepVo.getStatusDesc());
            setTransactionInfo.setStatus(transactionInfo.getStatus());
            setTransactionInfo.setTransactionScheme(transactionDepVo.getTransactionScheme());
            setTransactionInfo.setTransactionContent(transactionDepVo.getTransactionContent());
            transactionInfoMapper.updateByPrimaryKey(setTransactionInfo);

            if(null != transactionDepVo.getDepIds()){
                for (String depId : transactionDepVo.getDepIds()){
                    TransactionDepartmentMid mid = new TransactionDepartmentMid();
                    mid.setId(UUID.randomUUID().toString().replace("-",""));
                    mid.setDepartmentId(depId);
                    mid.setTransactionId(transactionInfo.getId());
                    mid.setCreateTime(new Date());
                    mid.setUpdateTime(new Date());
                    transactionDepartmentMidMapper.insert(mid);
                }
            }
        }
        return 0;
    }

    @Override
    public TransactionInfo findTransactionById(String id) throws Exception {
        return transactionInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int findByName(String tName) throws Exception {
        return transactionInfoMapper.findByName(tName);
    }

    @Override
    public int saveTranactionInfo(TransactionInfo transactionInfo) throws Exception {
     return saveTranactionInfo(transactionInfo);
    }

    @Override
    public TransactionInfoVo findTransactionInfoVoById(String id) throws Exception {
        return transactionInfoMapper.selectTransactionInfoVo(id);
    }

    @Override
    public Map<String, Object> findCheckDepartmentByPid(String pid, String searchName) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        List<DepartmentInfo> departmentAll = departmentInfoMapper.findAllDepartment(searchName);
        List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
        if( !StringUtils.isEmpty(pid)){
            departmentInfos = departmentInfoMapper.findDepartmentByPid(pid);
            List<String> list = new ArrayList<String>();
            for(DepartmentInfo departmentInfo : departmentInfos){
                list.add(departmentInfo.getId());
            }
            for(DepartmentInfo departmentInfo :departmentAll){
                departmentInfo.setIsCheck("0");
                if(list.contains(departmentInfo.getId())){
                    departmentInfo.setIsCheck("1");
                }
            }
        }
        map.put("departmentAll",departmentAll);
        map.put("departmentInfos",departmentInfos);
        return map;
    }


}
