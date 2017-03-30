package com.boonya.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 系统访问控制层基类
 * 
 * @packge com.wlyd.fmcgwms.controller.BaseController
 * @date 2016年4月29日 下午1:08:06
 * @author pengjunlin
 * @comment
 * @update
 */
public abstract class BaseController {
	
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	
	
}