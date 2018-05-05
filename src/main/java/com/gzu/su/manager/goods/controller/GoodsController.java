package com.gzu.su.manager.goods.controller;

import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.goods.model.GoodsInfo;
import com.gzu.su.manager.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "add_info")
    public String info(Model model,String pid,String sign){
        try {
            GoodsInfo goodsInfo = new GoodsInfo();
            if(!StringUtils.isEmpty(pid)){
             goodsInfo = goodsService.findGoodsById(pid);
            }
            model.addAttribute("goodsInfo", goodsInfo);
            model.addAttribute("sign",sign);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "goods/goods_info";
    }

    @RequestMapping(value = "lend_return")
    public String lendInfo(Model model,String pid,String sign) throws Exception{
        try {
            GoodsInfo goodsInfo = new GoodsInfo();
            if(!StringUtils.isEmpty(pid)){
                goodsInfo = goodsService.findGoodsById(pid);
            }
            model.addAttribute("goodsInfo", goodsInfo);
            model.addAttribute("sign",sign);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "goods/lend_info";
    }

    @ResponseBody
    @RequestMapping(value = "/page")
    public PageResult<GoodsInfo> page(Integer page, Integer limit, String searchName) throws Exception {
        PageResult<GoodsInfo> result = new PageResult<GoodsInfo>();
        try {
            Integer startNum = 0;
            Integer size = limit;
            if (page > 0) {
                startNum = (page - 1) * size;
            }
            result = goodsService.findByPage(startNum, size, searchName);
            result.setCode(0);
            result.setMsg("success");
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
    public MapResult insertParam(String pid,String goodsName, String goodsPrice, String goodsNumber,String goodsBuyTime) throws Exception{
        try {
            goodsService.insertParam(pid,goodsName,goodsPrice,goodsNumber,goodsBuyTime);
            return MapResult.ok(200, "保存成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return MapResult.error(-1, "保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public MapResult delete(String pid) throws Exception{
        try {
            goodsService.deleteByPrimaryKey(pid);
            return MapResult.ok(200,"删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            return MapResult.error(-1,"删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public MapResult update( GoodsInfo goodsInfo) throws Exception{
        try {
            int i =  goodsService.updateByPrimaryKey(goodsInfo);
            if(i != 0){
                return MapResult.ok(0, "失败");
            }else {
                return MapResult.ok(200, "成功");
            }

        }catch (Exception e){
            log.error(e.getMessage());
            return MapResult.error(-1,"操作失败");
        }
    }
}
