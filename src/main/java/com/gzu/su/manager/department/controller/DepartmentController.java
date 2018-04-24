package com.gzu.su.manager.department.controller;

import com.gzu.su.manager.common.response.MapResult;
import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.department.model.DepartmentInfo;
import com.gzu.su.manager.department.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: jack.ye
 * @create: 2018/04/21 15:55
 * @description:部门管理
 **/
@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

    private static Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/list")
    public String list() throws Exception {
        return "department/department_list";
    }

    @RequestMapping(value = "/department_info")
    public String info(Model model, String pid, String sign){
        try {
            DepartmentInfo departmentInfo = new DepartmentInfo();
            if(!StringUtils.isEmpty(pid)){
                departmentInfo = departmentService.findDepartmentById(pid);
            }
            model.addAttribute("dp", departmentInfo);
            model.addAttribute("sign",sign);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "department/department_info";
    }

    @ResponseBody
    @RequestMapping(value = "/page")
    public PageResult<DepartmentInfo> page(Integer page, Integer limit, String searchName) {
        PageResult<DepartmentInfo> result = new PageResult<DepartmentInfo>();
        try {
            Integer startNum = 0;
            Integer size = limit;
            if (page > 0) {
                startNum = (page - 1) * size;
            }
            result.setCode(0);
            result.setMsg("success");
            result = departmentService.findByPage(startNum, size, searchName);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setCode(-1);
            result.setMsg("failed");
            return result;
        }
    }

    @RequestMapping(value = "insert")
    @ResponseBody
    public MapResult insert(DepartmentInfo departmentInfo) throws Exception{
        try {
            int i = departmentService.insert(departmentInfo);
            if(i == 0){
                return MapResult.ok(-1, "保存失败");
            }
            return MapResult.ok(200, "保存成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return MapResult.error(-1, "保存失败");
        }
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public MapResult delete(String pid) throws Exception{
        try {
            int i = departmentService.deleteByPrimaryKey(pid);
            if(i == 0){
                return MapResult.ok(-1, "保存失败");
            }
            return MapResult.ok(200, "保存成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return MapResult.error(-1, "保存失败");
        }
    }
}
