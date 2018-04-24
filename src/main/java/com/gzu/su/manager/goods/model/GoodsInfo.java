package com.gzu.su.manager.goods.model;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GoodsInfo {

    private String id;

    private String goodsName;

    private Date goodsBuyTime;

    private String goodsPrice;

    private String goodsLender;

    private String goodsNumber;

    private String goodsReturner;

    private Date lendTime;

    private Date returnTime;

    private Date createTime;

    private Date updateTime;

    private String goodsMargin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsBuyTime() {
        if(!StringUtils.isEmpty(goodsBuyTime)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(goodsBuyTime);
        }
        else {
            return null;
        }
    }

    public void setGoodsBuyTime(Date goodsBuyTime) {
        this.goodsBuyTime = goodsBuyTime;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice == null ? null : goodsPrice.trim();
    }

    public String getGoodsLender() {
        return goodsLender;
    }

    public void setGoodsLender(String goodsLender) {
        this.goodsLender = goodsLender == null ? null : goodsLender.trim();
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber == null ? null : goodsNumber.trim();
    }

    public String getGoodsReturner() {
        return goodsReturner;
    }

    public void setGoodsReturner(String goodsReturner) {
        this.goodsReturner = goodsReturner == null ? null : goodsReturner.trim();
    }

    public String getLendTime() {
        if(!StringUtils.isEmpty(lendTime)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(lendTime);
        }
        else {
            return null;
        }
    }

    public void setLendTime(Date lendTime) {
        this.lendTime = lendTime;
    }

    public String getReturnTime() {
        if(!StringUtils.isEmpty(returnTime)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(returnTime);
        }
        else {
            return null;
        }
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getCreateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGoodsMargin() {
        return goodsMargin;
    }

    public void setGoodsMargin(String goodsMargin) {
        this.goodsMargin = goodsMargin == null ? null : goodsMargin.trim();
    }
}