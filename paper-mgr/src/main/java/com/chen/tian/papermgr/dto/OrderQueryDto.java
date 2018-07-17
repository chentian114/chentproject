package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by ChenTian on 2018/7/3.
 */
@Getter
@Setter
public class OrderQueryDto {
    private String orderNumber;
    private String customerName;
    private String customerPhone;
    private String salesName;
    private Set<Long> salesIds;
    private Set<Long> customerIds;

    public OrderQueryDto(String orderNumber, String customerName, String customerPhone, String salesName) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.salesName = salesName;
    }

}
