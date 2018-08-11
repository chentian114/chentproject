package com.chen.tian.papermgr.util;

import com.chen.tian.papermgr.dto.ResultInfo;

public class ResultInfoUtils {

	public static final int RESULT_CODE_SUCESS = 0;	//成功
	public static final String RESULT_SUCESS = "success";

	public static final int RESULT_CODE_FAIL = -1;	//失败
	public static final String RESULT_FAIL = "fail";

	public static ResultInfo success(){
		ResultInfo info = new ResultInfo();
		info.setResultCode(RESULT_CODE_SUCESS);
		info.setResultDetail(RESULT_SUCESS);
		return info;
	}

	public static ResultInfo success(String data){
		ResultInfo info = new ResultInfo();
		info.setResultCode(RESULT_CODE_SUCESS);
		info.setResultDetail(RESULT_SUCESS);
		info.setResultData(data);
		return info;
	}

	public static ResultInfo error(){
		ResultInfo info = new ResultInfo();
		info.setResultDetail(RESULT_FAIL);
		info.setResultCode(RESULT_CODE_FAIL);
		return info;
	}

	public static ResultInfo error(String message){
		ResultInfo info = new ResultInfo();
		info.setResultCode(RESULT_CODE_FAIL);
		info.setResultDetail(message);
		return info;
	}
	public static ResultInfo error(int code, String message){
		ResultInfo info = new ResultInfo();
		info.setResultDetail(message);
		info.setResultCode(code);
		return info;
	}

	public static boolean isSucess(ResultInfo resultInfo){
		if(resultInfo.getResultCode() == RESULT_CODE_SUCESS){
			return true;
		}
		return false;
	}

}
