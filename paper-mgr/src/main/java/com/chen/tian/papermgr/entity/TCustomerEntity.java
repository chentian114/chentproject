package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户表
 * Created by ChenTian on 2018/5/9.
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "t_customer")
public class TCustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private Long id;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "sales_id")
    private String salesId;
    @Column(name = "address")
    private String address;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "phone")
    private String phone;
    @Column(name = "type")
    private String type;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    @Column(name = "memo")
    private String memo;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "state")
    private Integer state;  //-1:已删除0:正常

    @Transient
    private TSalesmanEntity salesmanEntity;

    @Transient
    private String salesName;
    @Transient
    private Long querySalesId;

}
