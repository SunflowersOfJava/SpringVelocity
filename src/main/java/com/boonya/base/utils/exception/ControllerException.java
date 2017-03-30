package com.boonya.base.utils.exception;

/**
 * 运行时Controller控制层异常类
 * 
 * @packge com.wlyd.fmcgwms.util.exception.ControllerException
 * @date 2016年4月28日 下午4:25:06
 * @author pengjunlin
 * @comment
 * @update  添加注释
 */
public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数
	 * @param msg
	 */
	public ControllerException(String msg) {
		super(msg);
	}

	/**
	 * 构造函数
	 * @param code
	 * @param cause
	 */
	public ControllerException(String code, Throwable cause) {
		super(code, cause);
	}
}