package com.chen.tian.papermgr.dto;

import lombok.Getter;

/**
 * Created by ChenTian on 2018/7/5.
 */
@Getter
public enum  OrderInfoEnum {
    CUSTOMER_INFO(2,2),ORDER_NUMBER(2,7),DELIVERY_ADDRESS(3,1),
    DELIVER_DATE(3,7),ORDER_PRODS_START(5,0),ORDER_PRODS_END(10,0),
    MONEY_COUNT_UPPER(11,1),MONEY_COUNT(11,6),PAYMENT_TYPE(15,0),
    USER(16,0),SALES_MAN(16,2),DELIVER_TYPE(16,5),

    CUSTOMER_INFO_2003(4,3),ORDER_NUMBER_2003(4,11),DELIVERY_ADDRESS_2003(5,2),
    DELIVER_DATE_2003(5,11),ORDER_PRODS_START_2003(7,0),ORDER_PRODS_END_2003(12,0),
    MONEY_COUNT_UPPER_2003(15,3),MONEY_COUNT_2003(15,10),PAYMENT_TYPE_2003(19,1),
    USER_2003(20,0),SALES_MAN_2003(20,5),DELIVER_TYPE_2003(20,9);

    private int rowNum ;
    private int cellNum ;

    private OrderInfoEnum(int rowNum,int cellNum){
        this.rowNum = rowNum;
        this.cellNum = cellNum;
    }
}