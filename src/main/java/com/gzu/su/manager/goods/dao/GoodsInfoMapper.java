package com.gzu.su.manager.goods.dao;

import com.gzu.su.manager.goods.model.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfo> findByPage(@Param("startNum") Integer startNum, @Param("size") Integer size, @Param("gName") String gName) throws Exception;

    Integer findByPageCount(@Param("gName") String gName) throws Exception;

    GoodsInfo findByName(@Param("gName") String gName) throws Exception;

}