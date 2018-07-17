package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by ChenTian on 2018/7/2.
 */
@Setter
@Getter
@SuppressWarnings("serial")
public class OrderProdDto implements Serializable {
    private String productId;
    private String prodName;    //品名
    private String gweight;    //克重(g)
    private String specType;    //规格类型
    private String spec;        //规格(cm)
    private String amount;       //数量
    private String unit;        //单位
    private String unitPrice;    //单价（元/吨）
    private String money;        //金额(元)
    private String memo;        //备注

}
