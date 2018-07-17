package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 货单表
 * Created by ChenTian on 2018/5/9.
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "t_order")
public class TOrderEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "price")
    private Double moneyCount;
    @Column(name = "price_upper")
    private String moneyCountUpper;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "sales_id")
    private Long salesId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "customer_phone")
    private String customerPhone;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "delivery_phone")
    private String deliveryPhone;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "deliver_type")
    private String deliverType;
    @Column(name = "deliver_date")
    private Date deliverDate;
    @Column(name = "state")
    private Integer state;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;


    @Transient
    private SysUser user;
    @Transient
    private TSalesmanEntity salesmanEntity;
    @Transient
    private TCustomerEntity customerEntity;
    @Transient
    private List<TOrderProdEntity> orderProductList;

}
