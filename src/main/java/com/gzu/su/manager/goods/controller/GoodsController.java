package com.gzu.su.manager.goods.controller;

import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.goods.model.GoodsInfo;
import com.gzu.su.manager.goods.service.GoodsService;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 物品管理
 *
 * @author: jack.ye
 * @create: 2018/04/17 19:59
 **/
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    GoodsService goodsService;

    /**
     * 返回物品维护页面
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "goods/goods_list";
    }

    @RequestMapping(value = "/statistic")
    public String lendAndReturn() {
        return "goods/goods_statistic";
    }

    @ResponseBody
    @RequestMapping(value = "/page")
    public PageResult<GoodsInfo> page(Integer page, Integer limit, String searchName) {
        PageResult<GoodsInfo> result = new PageResult<GoodsInfo>();
        try {
            Integer startNum = 0;
            Integer size = limit;
            if (page > 0) {
                startNum = (page - 1) * size;
            }
            result.setCode(0);
            result.setMsg("success");
            result = goodsService.findByPage(startNum, size, searchName);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setCode(-1);
            result.setMsg("failed");
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "insert")
    public MapResult insert(GoodsInfo goodsInfo) {
        try {
            goodsService.insert(goodsInfo);
            return MapResult.ok(200, "保存成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return MapResult.error(-1, "保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public MapResult delete(String id){
        try {
            goodsService.deleteByPrimaryKey(id);
            return MapResult.ok(200,"删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            return MapResult.error(-1,"删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public MapResult update(GoodsInfo goodsInfo) {
        try {
            goodsService.updateByPrimaryKey(goodsInfo);
            if (goodsInfo.getStatus().equals("1")) {
                return MapResult.ok(200, "借记成功");
            }else {
                return MapResult.ok(200,"归还成功");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return MapResult.error(-1,"操作失败");
        }
    }
}
