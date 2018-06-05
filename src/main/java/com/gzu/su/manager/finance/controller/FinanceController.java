package com.gzu.su.manager.finance.controller;

import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.department.service.DepartmentService;
import com.gzu.su.manager.finance.model.FinanceInfo;
import com.gzu.su.manager.finance.service.FinanceService;
import com.gzu.su.manager.goods.model.GoodsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "finance")
public class FinanceController {

    private static Logger log = LoggerFactory.getLogger(FinanceController.class);

    @Autowired
    FinanceService financeService;

    @Autowired
    DepartmentService departmentService;
    @RequestMapping(value = "list")
    public String list(){
        return "finance/finance_list";
    }

    @RequestMapping(value = "check_list")
        public String checkList(){return "finance/finance_check";}

    @RequestMapping(value = "info")
    public String info(Model model, String pid, String sign){
        try {
            FinanceInfo financeInfo = new FinanceInfo();
            List<DepartmentInfo> departmentInfoList = departmentService.findAllDepartment();
            if(!StringUtils.isEmpty(pid)){
                financeInfo = financeService.findFinanceById(pid);
            }
            model.addAttribute("financeInfo", financeInfo);
            model.addAttribute("departmentInfoList", departmentInfoList);
            model.addAttribute("sign",sign);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "finance/finance_info";
    }

    @ResponseBody
    @RequestMapping(value = "/page")
    public PageResult<FinanceInfo> page(Integer page, Integer limit, String searchName) throws Exception {
        PageResult<FinanceInfo> result = new PageResult<FinanceInfo>();
        try {
            Integer startNum = 0;
            Integer size = limit;
            if (page > 0) {
                startNum = (page - 1) * size;
            }
            result = financeService.findByPage(startNum, size, searchName);
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
    @RequestMapping(value = "/check_page")
    public PageResult<FinanceInfo> checkPage(Integer page, Integer limit, String searchName) throws Exception {
        PageResult<FinanceInfo> result = new PageResult<FinanceInfo>();
        try {
            Integer startNum = 0;
            Integer size = limit;
            if (page > 0) {
                startNum = (page - 1) * size;
            }
            result = financeService.checkByPage(startNum, size, searchName);
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
    public MapResult saveTran(@RequestBody FinanceInfo financeInfo) throws Exception{
        try {
            if(StringUtils.isEmpty(financeInfo.getId())){
                financeService.insert(financeInfo);
                return MapResult.ok(200,"保存成功");
            }else {
                financeService.updateByPrimaryKey(financeInfo);
                return MapResult.ok(200,"保存成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return MapResult.error(-1,"保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public MapResult delete(String pid) throws Exception{
        try {
            financeService.deleteByPrimaryKey(pid);
            return  MapResult.ok(200,"删除成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return MapResult.error(-1,"删除失败");
        }
    }

}
