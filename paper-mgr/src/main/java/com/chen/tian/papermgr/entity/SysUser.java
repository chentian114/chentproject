package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *	用户表
 */

@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name="sys_user")
public class SysUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private Long id; //ID
	@Column(name = "account")
	private String account; //登录账号
	@Column(name = "description")
	private String description; //描述
	@Column(name = "city_id")
	private String cityId;		//地市
	@Column(name = "password")
	private String password; //密码
	@Column(name="name")
	private String name; //名称
	@Column(name = "create_id")
	private String createId; //创建人
	@Column(name = "phone")
	private String phone; //电话
	@Column(name = "email")
	private String email; //邮箱
	@Column(name = "create_time")
	private Date createTime; // 创建时间
	@Column(name = "update_time")
	private Date updateTime; //更新时间
	@Column(name = "state")
	private Integer state; //状态：-1删除，0：未登录，1：登陆过


	@Column(name = "role_id")
	private Long roleId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable=false, updatable=false)
	private SysRole role ; // 角色ID

	public SysUser(){
	}
	public SysUser(String account,String password){
		this.account = account;
		this.password = password;
	}
}
