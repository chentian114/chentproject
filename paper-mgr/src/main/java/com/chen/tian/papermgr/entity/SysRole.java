package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 角色表
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "sys_role")
public class SysRole implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "name")
	private String name;		//角色名称
	@Column(name = "creater_name")
	private String createrName;	//创建人
	@Column(name = "description")
	private String description;	//描述
	@Column(name = "create_time")
	private Date createTime;	//创建时间
	@Column(name = "update_time")
	private Date updateTime;	//更新时间


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sys_role_resource", schema = "",
			joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) })
	private Set<SysResource> resources = new HashSet<>(0); //管理权限

}