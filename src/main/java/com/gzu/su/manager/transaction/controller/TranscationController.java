package com.gzu.su.manager.transaction.controller;

import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.department.service.DepartmentService;
import com.gzu.su.manager.transaction.model.vo.TransactionDepVo;
import com.gzu.su.manager.transaction.model.vo.TransactionInfoVo;
import com.gzu.su.manager.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: jack.ye
 * @create: 2018/04/16 21:17
 * @description: 事务管理接口
 **/
@Controller
@RequestMapping(value = "/transaction")
public class TranscationController {

    private static Logger log = LoggerFactory.getLogger(TranscationController.class);

    @Autowired
    TransactionService transactionService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "list")
    public String list() throws Exception{
        return "transaction/transaction_list";
    }

    @RequestMapping(value = "check_list")
    public String checkList() throws Exception{
        return "transaction/transaction_check";
    }

    @RequestMapping(value = "info")
    public String info(Model model,String pid, String sign) throws Exception{
        try {
            TransactionInfoVo transactionInfoVo = new TransactionInfoVo();
            if(!StringUtils.isEmpty(pid) && !StringUtils.isEmpty(sign)){
                transactionInfoVo = transactionService.findTransactionInfoVoById(pid);
            }
            if("add".equals(sign)){
                transactionInfoVo.setDepartmentInfos(departmentService.findAllDepartment());
            }
            model.addAttribute("transactionInfoVo",transactionInfoVo);
            model.addAttribute("sign",sign);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return "transaction/transaction_info";
    }

    @RequestMapping(value = "page")
    @ResponseBody
    public PageResult<TransactionInfoVo> page(Integer page, Integer limit, String searchName) {
        PageResult<TransactionInfoVo> result = new PageResult<TransactionInfoVo>();
        try {
            Integer startNum = 0;
            Integer size = limit;
            if (page > 0) {
                startNum = (page - 1) * size;
            }
            result = transactionService.findByPage(startNum, size, searchName);
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

    @RequestMapping(value = "check_page")
    @ResponseBody
    public PageResult<TransactionInfoVo> checkPage(Integer page, Integer limit, String searchName) {
        PageResult<TransactionInfoVo> result = new PageResult<TransactionInfoVo>();
        try {
            Integer startNum = 0;
            Integer size = limit;
            if (page > 0) {
                startNum = (page - 1) * size;
            }
            result = transactionService.checkByPage(startNum, size, searchName);
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
    public MapResult saveTran(@RequestBody TransactionDepVo transactionDepVo) throws Exception{
        try {
            if(StringUtils.isEmpty(transactionDepVo.getId())){
                transactionService.saveTransactionInfoVo(transactionDepVo);
                return MapResult.ok(200,"保存成功");
            }else {
                transactionService.updateTransactionInfoVo(transactionDepVo);
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
            transactionService.deleteByPrimaryKey(pid);
            return  MapResult.ok(200,"删除成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return MapResult.error(-1,"删除失败");
        }
    }

    /**
     * 添加活动部门页
     *
     * @param model
     * @param pid 事务id
     * @param sign 页面标记，add:新增页，edit：修改页，show:查看页
     * @return
     */
    @RequestMapping(value = "department")
    public String departmentList(Model model,String pid,String sign){
        try {
            Map<String, Object> map = transactionService.findCheckDepartmentByPid(pid,"");
            model.addAttribute("departmentAll", map.get("departmentAll"));
            model.addAttribute("departmentInfos", map.get("departmentInfos"));
            model.addAttribute("pagesign", sign);
            model.addAttribute("transactionId", pid);
            return "transaction/transaction_iframe";
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return "transaction/transaction_iframe";
        }
    }

    @ResponseBody
    @RequestMapping(value = "searchDepart")
    public List<DepartmentInfo> searchDepart(String pid , String searchname){
        try {
            Map<String, Object> map = transactionService.findCheckDepartmentByPid(pid, searchname);
            List<DepartmentInfo> list = new ArrayList<DepartmentInfo>();
            if(null != map.get("merchantAll")){
                list = (List<DepartmentInfo>) map.get("merchantAll");
            }
            return list;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
