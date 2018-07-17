package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 货单表
 * Created by ChenTian on 2018/5/9.
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "t_sequence")
public class TSequenceEntity implements Serializable{
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "current_value")
    private Integer currentValue;
    @Column(name = "increment")
    private Integer increment;
    @Column(name = "create_time")
    private Date createTime;
}
