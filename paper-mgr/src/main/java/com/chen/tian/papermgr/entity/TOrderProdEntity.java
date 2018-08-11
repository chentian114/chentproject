package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 工单表
 * Created by ChenTian on 2018/5/9.
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "t_order_prod")
public class TOrderProdEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private Long id;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String prodName;    //品名
    @Column(name = "gweight")
    private String gweight;    //克重(g)
    @Column(name = "spec_type")
    private String specType;    //规格类型
    @Column(name = "spec")
    private String spec;        //规格(cm)
    @Column(name = "amount")
    private String amount;       //数量
    @Column(name = "unit")
    private String unit;        //单位
    @Column(name = "unit_price")
    private String unitPrice;    //单价（元/吨）
    @Column(name = "memo")
    private String memo;        //备注
    @Column(name = "weight_memo")
    private String weightmemo;        //重量备注
    @Column(name = "price")
    private Double money;
    @Column(name = "state")
    private Integer state;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private TProductEntity productEntity;


}
