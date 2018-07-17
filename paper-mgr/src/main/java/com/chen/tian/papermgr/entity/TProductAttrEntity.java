package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 产品属性配置表
 * Created by ChenTian on 2018/5/9.
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "t_product_attr")
public class TProductAttrEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private Long id;
    @Column(name = "prod_attr_name")
    private String prodAttrName;
    @Column(name = "value_type")
    private Integer valueType;
    @Column(name = "value_list")
    private String valueList;
    @Column(name = "value_description")
    private String valueDescription;
    @Column(name = "value_default")
    private String valueDefault;
    @Column(name = "value_range")
    private String valueRange;
    @Column(name = "value_begin")
    private String valueBegin;
    @Column(name = "value_end")
    private String valueEnd;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "state")
    private Integer state;
    @Column(name = "memo")
    private String memo;

    @Transient
    private List<String> valuesList;
    @Transient
    private List<String> valueDescriptionList;
    @Transient
    private List<String> specAreaList;
    @Transient
    private List<String> specWideList;
    @Transient
    private List<String> specAreaDescriptionList;
    @Transient
    private List<String> specWideDescriptionList;

}
