package com.boonya.base.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 字符串打印输出类
 * 
 * @packge com.wlyd.fmcgwms.util.exception.StringPrintWriter
 * @date 2016年4月28日 下午4:32:04
 * @author pengjunlin
 * @comment
 * @update 添加注释
 */
public class StringPrintWriter extends PrintWriter {

	/**
	 * 构造函数
	 */
	public StringPrintWriter() {
		super(new StringWriter());
	}

	/**
	 * 构造函数
	 * 
	 * @param initialSize
	 */
	public StringPrintWriter(int initialSize) {

		super(new StringWriter(initialSize));
	}

	/**
	 * 获取string输出值
	 * 
	 * @MethodName: getString
	 * @Description:
	 * @return
	 * @throws
	 */
	public String getString() {
		flush();
		return ((StringWriter) this.out).toString();
	}

	/**
	 * tostring
	 */
	@Override
	public String toString() {
		return getString();
	}
}