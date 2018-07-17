package com.chen.tian.papermgr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 资源权限表
 */
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
@Table(name = "sys_resource")
public class SysResource implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "name")
	private String name;		//名称
	@Column(name = "code")
	private String code;		//编码
	@Column(name = "url")
	private String url;			//地址
	@Column(name = "description")
	private String description;	//描述
	@Column(name = "seq")
	private Integer seq;		//顺序
	@Column(name = "create_time")
	private Date createTime;	//创建时间
	@Column(name = "update_time")
	private Date updateTime;	//更新时间
	//@Column(name = "parent_id")
	//private Integer parentId;	//上级ID

	@ManyToOne(targetEntity =SysResource.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private SysResource parent;	//上级ID
	@OneToMany( mappedBy = "parent", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<SysResource> children = new HashSet<SysResource>(0); //下级权限


}