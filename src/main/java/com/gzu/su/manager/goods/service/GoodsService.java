package com.gzu.su.manager.goods.service;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.goods.model.GoodsInfo;

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

    int deleteByPrimaryKey(String id);

    int insert(GoodsInfo goodsInfo);

    int updateByPrimaryKey(GoodsInfo record);
}
