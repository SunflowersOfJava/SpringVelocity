package com.boonya.base.utils.exception;
/**
 * 运行时service接口异常类
 * @packge com.wlyd.fmcgwms.util.exception.ServiceException
 * @date   2016年4月28日  下午4:29:52
 * @author pengjunlin
 * @comment   
 * @update  添加注释
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数
	 * @param msg
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * 构造函数
	 * @param code
	 * @param cause
	 */
	public ServiceException(String code, Throwable cause) {
		super(code, cause);
	}
}