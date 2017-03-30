package com.boonya.base.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件名称过滤器类
 * 
 * @packge com.wlyd.fmcgwms.util.Filter
 * @date 2016年4月29日 上午11:27:59
 * @author pengjunlin
 * @comment
 * @update 添加注释
 */
public class Filter implements FilenameFilter {
	
	String extension;

	public Filter(String extension) {
		this.extension = extension;
	}

	public boolean accept(File myFile, String filename) {
		return filename.endsWith("." + extension);
	}
}
