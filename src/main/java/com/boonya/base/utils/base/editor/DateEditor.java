package com.boonya.base.utils.base.editor;

import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;
import com.boonya.base.utils.DateUtil;

/**
 * 日期编辑器类
 * 
 * @packge com.wlyd.fmcgwms.util.base.editor.DateEditor
 * @date 2016年4月28日 下午2:37:37
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public class DateEditor extends PropertyEditorSupport {

	/**
	 * 字符串转Date对象存储
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(DateUtil.string2Date(text, "yyyy-MM-dd"));
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
