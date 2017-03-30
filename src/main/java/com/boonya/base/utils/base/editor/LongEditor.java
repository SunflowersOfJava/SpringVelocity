package com.boonya.base.utils.base.editor;

import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;

/**
 * Long数字编辑器类
 * 
 * @packge com.wlyd.fmcgwms.util.base.editor.LongEditor
 * @date 2016年4月28日 下午3:10:40
 * @author pengjunlin
 * @comment
 * @update
 */
public class LongEditor extends PropertyEditorSupport {

	/**
	 * 字符串转Long类型对象存储
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals(""))
			text = "0";
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			// 这句话是最重要的，他的目的是通过传入参数的类型来匹配相应的databind
			setValue(Long.parseLong(text));
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
