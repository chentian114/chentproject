package com.chen.tian.papermgr.controller;

import com.alibaba.fastjson.JSON;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.dto.ReturnDto;
import com.chen.tian.papermgr.entity.TProductEntity;
import com.chen.tian.papermgr.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 产品管理模块控制层
 * Created by ChenTian on 2018/5/11.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/productList.do")
    @ResponseBody
    public String productList(@RequestParam(value="prodName", required = false) String prodName,
                               @RequestParam(value="page_num", required = true) Integer pageNumber,
                               @RequestParam(value="page_size", required = true) Integer pageSize){
        logger.info("pageNumber:{} pageSize:{}",pageNumber,pageSize);
        ReturnDto returnDto = new ReturnDto(true);
        try {
            QueryResult<TProductEntity> result = productService.findProductByPageAndParams(prodName, pageNumber, pageSize);
            returnDto.setData(result);
        }catch (Exception e) {
            logger.error("productList prodName:{} error!",prodName, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value = "/productAll.do")
    @ResponseBody
    public String productAll(){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            List<TProductEntity> result = productService.findAllProduct();
            returnDto.setData(result);
        }catch (Exception e) {
            logger.error("productAll  error!", e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value = "/productDetail.do")
    @ResponseBody
    public String productDetail(@RequestParam(value="id", required = true) Long productId){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            TProductEntity product = productService.productDetail(productId);
            returnDto.setData(product);
        }catch (Exception e) {
            logger.error("productDetail productId:{} error!",productId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }



    @RequestMapping(value = "/deleteProduct.do")
    @ResponseBody
    public String deleteProduct(@RequestParam(value="id", required = true) Long productId){
        logger.info("salesId:{}",productId);
        ReturnDto returnDto = new ReturnDto(true);
        try {
            productService.deleteProduct(productId);
        }catch (Exception e) {
            logger.error("deleteProduct productId:{} error!",productId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }

        return JSON.toJSONString(returnDto);
    }


    @RequestMapping(value="/productAddOrUpdate.do" , method = RequestMethod.POST)
    @ResponseBody
    public String productAddOrUpdate(TProductEntity productEntity) {
        logger.info("proudct:{}",JSON.toJSONString(productEntity));
        ReturnDto<TProductEntity> returnDto = new ReturnDto<>(true);
        try {
            productEntity = productService.productAddOrUpdate(productEntity);
            returnDto.setData(productEntity);
        } catch (Exception e) {
            logger.error("productAddOrUpdate error product:{}",JSON.toJSONString(productEntity), e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }

        return JSON.toJSONString(returnDto);
    }



}
