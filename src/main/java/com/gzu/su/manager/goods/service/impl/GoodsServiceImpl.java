package com.gzu.su.manager.goods.service.impl;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.goods.dao.GoodsInfoMapper;
import com.gzu.su.manager.goods.model.GoodsInfo;
import com.gzu.su.manager.goods.service.GoodsService;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
    public int insertParam(String id,String goodsName, String goodsPrice, String goodsNumber,String goodsBuyTime) throws Exception {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsName(goodsName);
        goodsInfo.setGoodsPrice(goodsPrice);
        goodsInfo.setCreateTime(new Date());
        goodsInfo.setUpdateTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(goodsBuyTime);
        goodsInfo.setGoodsBuyTime(date);
        goodsInfo.setGoodsNumber(goodsNumber);
        goodsInfo.setGoodsMargin(goodsNumber);
        GoodsInfo info = goodsInfoMapper.findByName(goodsName);
        int margin=0 ,number=0;
        if(info != null){
            //判断库里是否有余、数量并作相加操作
            margin = Integer.parseInt(info.getGoodsMargin());
            margin = margin + Integer.parseInt(goodsNumber);

            number = Integer.parseInt(info.getGoodsNumber());
            number = number + Integer.parseInt(goodsNumber);

            String gMargin = Integer.toString(margin);
            String gNumber = Integer.toString( number);
            goodsInfo.setGoodsMargin(gMargin);
            goodsInfo.setGoodsMargin(gNumber);
        }
        if(StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString().replace("-", "");
            goodsInfo.setId(id);
            return goodsInfoMapper.insert(goodsInfo);
        }else {
            goodsInfo.setId(id);
            return goodsInfoMapper.updateByPrimaryKey(goodsInfo);
        }

    }

    @Override
    public int updateByPrimaryKey(GoodsInfo goodsInfo) throws Exception {
        int result = 0;
        if(!StringUtils.isEmpty(goodsInfo.getGoodsLender()) && !StringUtils.isEmpty(goodsInfo.getId())){
            String lendTime = goodsInfo.getLendTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(lendTime);
            goodsInfo.setLendTime(date);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsInfo.getId());
            int margin=0;
            if(info != null){
                //余量减少
                margin = Integer.parseInt(info.getGoodsMargin());
                margin = margin-Integer.parseInt(goodsInfo.getGoodsNumber());
                String gMargin = Integer.toString(margin);
                goodsInfo.setGoodsMargin(gMargin);
            }
            result = goodsInfoMapper.updateByPrimaryKey(goodsInfo);
        }
        if(!StringUtils.isEmpty(goodsInfo.getGoodsReturner()) && !StringUtils.isEmpty(goodsInfo.getId())){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date returnDate = sdf.parse(returnTime);
//            goodsInfo.setReturnTime(returnDate);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsInfo.getId());
            int margin=0;
            if(info != null){
                //余量减少
                margin = Integer.parseInt(info.getGoodsMargin());
                margin = margin + Integer.parseInt(goodsInfo.getGoodsNumber());
                String gMargin = Integer.toString(margin);
                goodsInfo.setGoodsMargin(gMargin);
            }
            result = goodsInfoMapper.updateByPrimaryKey(goodsInfo);
        }
        return result;
    }

    @Override
    public GoodsInfo findGoodsById(String id) throws Exception {
        return goodsInfoMapper.selectByPrimaryKey(id);
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2008-08-08");
        System.out.println(date);
    }
}
