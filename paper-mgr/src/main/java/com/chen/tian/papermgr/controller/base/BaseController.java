package com.chen.tian.papermgr.controller.base;


import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}
	
	protected HttpServletResponse getResponse() {
		HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder
				.getRequestAttributes()).getResponse();
		return resp;
	}
	
	protected HttpSession getSession() {
		return getRequest().getSession(true);
	}
	
	protected String getLoginUserId() {
		String userId = String.valueOf(getSession().getAttribute("loginUserId"));
		return userId;
	}
	
	
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setViewPath(){

	}
}
