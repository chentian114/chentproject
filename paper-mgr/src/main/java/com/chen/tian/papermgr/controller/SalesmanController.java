package com.chen.tian.papermgr.controller;

import com.alibaba.fastjson.JSON;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.dto.ReturnDto;
import com.chen.tian.papermgr.entity.TSalesmanEntity;
import com.chen.tian.papermgr.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 业务员管理模块控制层
 * Created by ChenTian on 2018/5/11.
 */
@Controller
@RequestMapping("/salesman")
public class SalesmanController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/salesmanList.do")
    @ResponseBody
    public String salesmanList(@RequestParam(value="sales_name", required = false) String salesName,
                               @RequestParam(value="phone", required = false) String phone,
                               @RequestParam(value="page_num", required = true) Integer pageNumber,
                               @RequestParam(value="page_size", required = true) Integer pageSize){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            QueryResult<TSalesmanEntity> result = orderService.findSalesmanByPageAndParams(salesName, phone, pageNumber, pageSize);
            returnDto.setData(result);
        }catch (Exception e) {
            logger.error("salesmanList salesName:{} error!",salesName, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value = "/salesmanDetail.do")
    @ResponseBody
    public String salesmanDetail(@RequestParam(value="id", required = true) Long salesmanId){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            TSalesmanEntity salesman = orderService.salesmanDetail(salesmanId);
            returnDto.setData(salesman);
        }catch (Exception e) {
            logger.error("salesmanDetail salesmanId:{} error!",salesmanId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }



    @RequestMapping(value = "/deleteSalesman.do")
    @ResponseBody
    public String deleteSalesman(@RequestParam(value="id", required = true) Long salesmanId){
        logger.info("salesId:{}",salesmanId);
        ReturnDto returnDto = new ReturnDto(true);
        try {
            orderService.deleteSalesman(salesmanId);
        }catch (Exception e) {
            logger.error("deleteSalesman salesmanId:{} error!",salesmanId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }


    @RequestMapping(value="/salesmanAddOrUpdate.do" , method = RequestMethod.POST)
    @ResponseBody
    public String salesmanAddOrUpdate(TSalesmanEntity salesmanEntity) {
        ReturnDto<TSalesmanEntity> returnDto = new ReturnDto<>(true);
        try {
            salesmanEntity = orderService.salesmanAddOrUpdate(salesmanEntity);
            returnDto.setData(salesmanEntity);
        } catch (Exception e) {
            logger.error("salesmanAddOrUpdate error salesman:{}",JSON.toJSONString(salesmanEntity), e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }



}
