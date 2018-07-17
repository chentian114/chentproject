package com.chen.tian.papermgr.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 查询中间对象
 */
@SuppressWarnings("serial")
@Getter
@Setter
public final class QueryResult<T> implements Serializable{

	private String resultCode;
	private String resultMsg;
	private Integer totalPages = null;//总页数
	private Long totalElements = null;//总记录数
	private Integer perPageSize = null;
	private Integer pageNumber = null;
	private List<T> results = null;

	public QueryResult() {
	}

	public QueryResult(Integer totalPages, Long totalElements, Integer perPageSize, Integer pageNumber,
			List<T> results) {
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.perPageSize = perPageSize;
		this.pageNumber = pageNumber;
		this.results = results;
	}

	public QueryResult(Integer totalPages, Long totalElements, List<T> results) {
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.results = results;
	}

	public QueryResult(Integer totalPages, Long totalElements, QueryPage queryPage, List<T> results) {
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		if (queryPage != null) {
			this.perPageSize = queryPage.getPerPageSize();
			this.pageNumber = queryPage.getPageNumber();
		}
		this.results = results;
	}

	public boolean hasResult() {
		if (results == null) {
			return false;
		}
		if (results.size() < 1) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		int size = 0 ;
		if(results!=null){
			size = results.size();
		}
		String toStr = String.format("QueryResult[total:%s, perPageSize:%s, pageNumber:%s, resultSize:%d].",
				totalElements, perPageSize, pageNumber, size);
		return toStr;
	}

	
}
