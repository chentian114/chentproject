package com.chen.tian.papermgr.controller;

import com.alibaba.fastjson.JSON;
import com.chen.tian.papermgr.dto.CustomerDto;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.dto.ReturnDto;
import com.chen.tian.papermgr.entity.TCustomerEntity;
import com.chen.tian.papermgr.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客户管理模块控制层
 * Created by ChenTian on 2018/5/11.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/customerList.do")
    @ResponseBody
    public String customerList(@RequestParam(value="name", required = false) String custName,
                               @RequestParam(value="phone", required = false) String phone,
                               @RequestParam(value="address", required = false) String address,
                               @RequestParam(value="sales_name", required = false) String salesName,
                               @RequestParam(value="page_num", required = true) Integer pageNumber,
                               @RequestParam(value="page_size", required = true) Integer pageSize){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            CustomerDto customerDto = new CustomerDto(custName, phone, address,salesName);

            QueryResult<TCustomerEntity> result = orderService.findCustomerByPageAndParams(customerDto, pageNumber, pageSize);
            returnDto.setData(result);
        }catch (Exception e) {
            logger.error("customerList custName:{} error!",custName, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value = "/customerDetail.do")
    @ResponseBody
    public String customerDetail(@RequestParam(value="id", required = true) Long customerId,
                                 @RequestParam(value="salesId", required = false) Long salesId
                                 ){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            TCustomerEntity customer = orderService.customerDetail(customerId);
            customer.setQuerySalesId(salesId);
            returnDto.setData(customer);
        }catch (Exception e) {
            logger.error("customerDetail customerId:{} error!",customerId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value = "/deleteCustomer.do")
    @ResponseBody
    public String deleteCustomer(@RequestParam(value="id", required = true) Long customerId){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            orderService.delCustomer(customerId);
        }catch (Exception e) {
            logger.error("delCustomer customerId:{} error!",customerId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }

        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value="/customerAddOrUpdate.do", method = RequestMethod.POST)
    @ResponseBody
    public String customerAddOrUpdate(TCustomerEntity customerEntity) {
        logger.info("customer:{}",JSON.toJSONString(customerEntity));
        ReturnDto<TCustomerEntity> returnDto = new ReturnDto<>(true);
        try {
            customerEntity = orderService.customerAddOrUpdate(customerEntity);
            returnDto.setData(customerEntity);
        } catch (Exception e) {
            logger.error("customerEdit error customer:{}",JSON.toJSONString(customerEntity), e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }

        return JSON.toJSONString(returnDto);
    }

}
