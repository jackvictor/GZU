package com.gzu.su.manager.transaction.controller;

import com.gzu.su.manager.common.response.PageResult;
import com.gzu.su.manager.transaction.model.TransactionInfo;
import com.gzu.su.manager.transaction.model.vo.TransactionInfoVo;
import com.gzu.su.manager.transaction.service.TransactionService;
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
 * @create: 2018/04/16 21:17
 * @description: 事务管理接口
 **/
@Controller
@RequestMapping(value = "/transaction")
public class TranscationController {

    private static Logger log = LoggerFactory.getLogger(TranscationController.class);

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "list")
    public String list() throws Exception{
        return "transaction/transaction_list";
    }

    @RequestMapping(value = "info")
    public String info(Model model,String id, String sign) throws Exception{
        try {
            TransactionInfo transactionInfo = new TransactionInfo();
            if(!StringUtils.isEmpty(id) && !StringUtils.isEmpty(sign)){
                transactionInfo = transactionService.findTransactionById(id);
                model.addAttribute("transactionInfo",transactionInfo);
                model.addAttribute("sign",sign);
            }
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
            result.setCode(0);
            result.setMsg("success");
            result = transactionService.findByPage(startNum, size, searchName);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setCode(-1);
            result.setMsg("failed");
            return result;
        }
    }


}
