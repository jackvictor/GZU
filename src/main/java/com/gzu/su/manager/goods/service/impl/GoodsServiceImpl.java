package com.gzu.su.manager.goods.service.impl;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.goods.dao.GoodsInfoMapper;
import com.gzu.su.manager.goods.model.GoodsInfo;
import com.gzu.su.manager.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Service(value = "goodsService")
public class GoodsServiceImpl implements GoodsService{

    private static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Override
    public PageResult<GoodsInfo> findByPage(Integer startNum, Integer size, String gName) throws Exception {
        PageResult<GoodsInfo> result = new PageResult<GoodsInfo>();
        result.setData(goodsInfoMapper.findByPage(startNum,size,gName));
        result.setCount(goodsInfoMapper.findByPageCount(gName));
        return result;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        if (!StringUtils.isEmpty(id)){
            return  goodsInfoMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int insert(GoodsInfo goodsInfo) {
        String id =  UUID.randomUUID().toString().replace("-","");
        goodsInfo.setId(id);
        goodsInfo.setCreateTime(new Date());
        goodsInfo.setUpdateTime(new Date());
        return  goodsInfoMapper.insert(goodsInfo);
    }

    @Override
    public int updateByPrimaryKey(GoodsInfo goodsInfo) {
        goodsInfo.setUpdateTime(new Date());
        return goodsInfoMapper.updateByPrimaryKey(goodsInfo);
    }
}
