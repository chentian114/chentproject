package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 控制层与服务层传输对象
 */
@Getter
@Setter
@ToString
public class ResultInfo {
	private int resultCode; //结果码
	private String resultDetail; //结果详情
	private String resultData; //结果数据
	public ResultInfo(){
	}
	
	public ResultInfo(int resultCode, String resultDetail,String resultData) {
		super();
		this.resultCode = resultCode;
		this.resultDetail = resultDetail;
		this.resultData = resultData;
	}

}
