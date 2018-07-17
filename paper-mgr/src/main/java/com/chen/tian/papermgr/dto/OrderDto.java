package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by ChenTian on 2018/7/2.
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class OrderDto implements Serializable {
    private String id ;
    private String customerId;
    private String customerPhone;   //客户联系方式
    private String deliveryAddress; //送货地址
    private String deliverDate;       //送货日期
    private String paymentType;     //结款方式
    private String deliverType;     //送货方式
    private String deliveryCarNo;   //送货车牌号
    private String deliveryPhone;   //送货员号码
    private String moneyCountUpper; //合计大写(元)
    private String moneyCount;       //合计小写(元)
    private String userId;            //制单员
    private String salesId;           //业务员
    private String prodParamList;


}
