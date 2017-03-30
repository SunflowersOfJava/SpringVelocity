package com.boonya.base.utils.base.editor;

import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;

/**
 * Double数字编辑器类
 * 
 * @packge com.wlyd.fmcgwms.util.base.editor.DoubleEditor
 * @date 2016年4月28日 下午2:40:26
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public class DoubleEditor extends PropertyEditorSupport {

	/**
	 * 字符串转Double对象存储
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals(""))
			text = "0";
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			// 这句话是最重要的，他的目的是通过传入参数的类型来匹配相应的databind
			setValue(Double.parseDouble(text));
		}
	}

	/**
	 * 对象tostring
	 */
	@Override
	public String getAsText() {

		return getValue().toString();
	}
}
