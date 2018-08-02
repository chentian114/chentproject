package com.chen.tian.papermgr.controller;

import com.alibaba.fastjson.JSON;
import com.chen.tian.papermgr.controller.base.BaseController;
import com.chen.tian.papermgr.dto.OrderDto;
import com.chen.tian.papermgr.dto.OrderQueryDto;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.dto.ReturnDto;
import com.chen.tian.papermgr.entity.TOrderEntity;
import com.chen.tian.papermgr.service.ExcelService;
import com.chen.tian.papermgr.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 订单管理模块控制层
 * Created by ChenTian on 2018/5/11.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderService orderService;
    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/orderList.do")
    @ResponseBody
    public String orderList(@RequestParam(value="orderNumber", required = false) String orderNumber,
                            @RequestParam(value="customerName", required = false) String customerName,
                            @RequestParam(value="customerPhone", required = false) String customerPhone,
                            @RequestParam(value="salesName", required = false) String salesName,
                            @RequestParam(value="page_num", required = true) Integer pageNumber,
                            @RequestParam(value="page_size", required = true) Integer pageSize){
        ReturnDto returnDto = new ReturnDto(true);
        logger.info("orderNumber:{} customerName:{} customerPhone:{} salesName:{}",orderNumber,customerName,customerPhone,salesName);
        try {
            OrderQueryDto orderQueryDto = new OrderQueryDto(orderNumber, customerName, customerPhone,salesName);

            QueryResult<TOrderEntity> result = orderService.findOrderByPageAndParams(orderQueryDto, pageNumber, pageSize);
            returnDto.setData(result);
        }catch (Exception e) {
            logger.error("orderList orderNumber:{} error!", orderNumber, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }



    @RequestMapping(value = "/orderDetail.do")
    @ResponseBody
    public String orderDetail(@RequestParam(value="id", required = true) Long orderId){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            TOrderEntity orderEntity = orderService.findOrderById(orderId);
            returnDto.setData(orderEntity);
        }catch (Exception e) {
            logger.error("orderDetail orderId:{} error!",orderId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }


    @RequestMapping(value="/orderAdd.do" , method = RequestMethod.POST)
    @ResponseBody
    public String orderAdd(OrderDto orderDto) {
        logger.info("order:{}",JSON.toJSONString(orderDto));
        ReturnDto<TOrderEntity> returnDto = new ReturnDto<>(true);
        try {
            TOrderEntity orderEntity = orderService.converOrderByOrderDto(orderDto);
            boolean chResult = orderService.checkOrderPrice(orderEntity);
            if(!chResult){
                logger.error("checkOrderPrice error 校验工单数据异常 order:{}", JSON.toJSONString(orderDto));
                returnDto.setStatus(false);
                returnDto.setDescription("校验工单数据异常，请重新录单！");
                return JSON.toJSONString(returnDto);
            }
            orderEntity = orderService.saveOrder(orderEntity);
            excelService.buildExcelByOrder(orderEntity);
            returnDto.setData(orderEntity);
        } catch (Exception e) {
            logger.error("orderAdd error order:{}",JSON.toJSONString(orderDto), e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }

        return JSON.toJSONString(returnDto);
    }


    @RequestMapping(value="/downloadOrder.do")
    public HttpServletResponse downloadOrder(HttpServletResponse response,@RequestParam(value="id", required = true) Long orderId ){
        logger.info("download orderId:{}",orderId);
        OutputStream toClient = null;
        try {
            TOrderEntity orderEntity = orderService.findOrderById(orderId);

            String filePath = excelService.buildToFilePath(orderEntity);

            String filename = "送货单"+orderEntity.getOrderNumber()+excelService.getExcelTemplateFileSuffix();

            File file = new File(filePath);

            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            String downloadFileName = URLEncoder.encode(filename,"UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadFileName + "\"");
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/msexcel");
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            logger.info("download order finish!");
        }catch (Exception e) {
            logger.error("downloadOrder error order:{}", orderId, e);
        }finally {
            try{
                if( null != toClient){
                    toClient.close();
                }
            }catch (IOException e){
                logger.error("下载出错 id:{}",orderId,e);
            }
        }
        return response;
    }




}
