package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询中间对象
 */
@Getter
@Setter
public class QueryPage {
	private Integer perPageSize = null;	// 每页大小
	private Integer pageNumber = null;	//页码

	public QueryPage() {
	}

	public QueryPage(Integer perPageSize, Integer pageNumber) {
		this.perPageSize = perPageSize;
		this.pageNumber = pageNumber;
	}

}
