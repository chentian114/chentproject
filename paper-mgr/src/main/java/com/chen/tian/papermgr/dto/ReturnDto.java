package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 视图层-控制层传输对象
 */
@SuppressWarnings("serial")
@Setter
@Getter
public class ReturnDto<T> implements Serializable {

	private Boolean status = true;

	private String description = null;

	private Integer code = null;
	
	private T data;

	public ReturnDto() {
		super();
	}

	public ReturnDto(Boolean status) {
		super();
		this.status = status;
	}

	public ReturnDto(Boolean status, String description) {
		super();
		this.status = status;
		this.description = description;
	}
}
