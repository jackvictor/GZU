package com.gzu.su.manager.goods.model;

import java.util.Date;

public class GoodsInfo {
    private String id;

    private String goodsName;

    private Date goodsBuyTime;

    private String goodsPrice;

    private String goodsLender;

    private String goodsReturner;

    private Date lendTime;

    private Date returnTime;

    private Date createTime;

    private Date updateTime;

    private String status;

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

    public Date getGoodsBuyTime() {
        return goodsBuyTime;
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

    public String getGoodsReturner() {
        return goodsReturner;
    }

    public void setGoodsReturner(String goodsReturner) {
        this.goodsReturner = goodsReturner == null ? null : goodsReturner.trim();
    }

    public Date getLendTime() {
        return lendTime;
    }

    public void setLendTime(Date lendTime) {
        this.lendTime = lendTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}