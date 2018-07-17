package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 业务员表
 * Created by ChenTian on 2018/5/9.
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "t_salesman")
public class TSalesmanEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private Long id;
    @Column(name = "sales_name")
    private String salesName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "sex")
    private String sex;
    @Column(name = "type")
    private Integer type;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "state")
    private Integer state;  //-1:已删除0:正常
    @Column(name = "memo")
    private String memo;
}
