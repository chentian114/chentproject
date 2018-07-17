package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户信息传输对象
 * Created by ChenTian on 2018/5/11.
 */
@Getter
@Setter
public class CustomerDto {
    private String custName;
    private String phone;
    private String address;
    private String salesName;
    private Set<Long> salesIds = new HashSet<>();

    public CustomerDto(){
    }
    public CustomerDto(String custName){
        this.custName = custName;
    }
    public CustomerDto(String custName,String phone,String address,String salesName){
        this.custName = custName;
        this.phone = phone;
        this.address = address;
        this.salesName = salesName;
    }
}
