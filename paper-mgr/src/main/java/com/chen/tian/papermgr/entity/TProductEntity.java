package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品配置表
 * Created by ChenTian on 2018/5/9.
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "t_product")
public class TProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private Long id;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "memo")
    private String memo;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "state")
    private Integer state;

    @Transient
    private List<TProductAttrEntity> prodAttrs = new ArrayList<>(0);
}
