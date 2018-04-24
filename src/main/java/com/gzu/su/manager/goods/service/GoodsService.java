package com.gzu.su.manager.goods.service;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.goods.model.GoodsInfo;

import java.util.Date;

/**
 * @author: User
 * @create: 2018/04/17 11:25
 * @description: 物品service层接口
 **/
public interface GoodsService {

    /**
     * 按页查询物品
     *
     * @param startNum
     * @param size
     * @param gName
     * @return
     * @throws Exception
     */
    PageResult<GoodsInfo> findByPage(Integer startNum, Integer size, String gName) throws Exception;

    int deleteByPrimaryKey(String id) throws Exception;

    int insert(GoodsInfo goodsInfo) throws Exception;

    int insertParam(String id,String goodsName , String goodsPrice, String goodsBuyTime,String goodsNumber) throws Exception;

    int updateByPrimaryKey(GoodsInfo goodsInfo) throws Exception;

    GoodsInfo findGoodsById(String id) throws Exception;
}
