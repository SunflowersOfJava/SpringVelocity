package com.boonya.base.utils.downLoad;

import java.util.Comparator;
import java.util.Hashtable;

/**
 * 文件大小比较器
 * 
 * @packge com.wlyd.fmcgwms.util.downLoad.SizeComparator
 * @date 2016年4月28日 下午4:05:33
 * @author arrow
 * @comment
 * @update pengjunlin 2016年4月28日 下午15:27:50 添加注释
 */
@SuppressWarnings("rawtypes")
public class SizeComparator implements Comparator {

	/**
	 * 比较两个文件的大小
	 */
	public int compare(Object a, Object b) {
		// TODO Auto-generated method stub
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir"))
				&& ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
				return 1;
			} else if (((Long) hashA.get("filesize")) < ((Long) hashB
					.get("filesize"))) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
