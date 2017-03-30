package com.boonya.base.utils.base;

import java.io.Serializable;

/**
 * JsonBean 字符串类
 * 
 * @packge com.wlyd.fmcgwms.util.base.JsonBean
 * @date 2016年4月28日 下午2:34:07
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public class JsonBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8130202126056941888L;

	private String json;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
