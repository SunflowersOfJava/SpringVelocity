package com.boonya.base.utils.downLoad;

import java.util.Comparator;
import java.util.Hashtable;

/**
 * 文件名称先后比较器
 * 
 * @packge com.wlyd.fmcgwms.util.downLoad.NameComparator
 * @date 2016年4月28日 下午4:02:43
 * @author arrow
 * @comment
 * @update pengjunlin 2016年4月28日 下午15:27:50 添加注释
 */
@SuppressWarnings("rawtypes")
public class NameComparator implements Comparator {

	/**
	 * 比较两个文件的名称
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
			return ((String) hashA.get("filename")).compareTo((String) hashB
					.get("filename"));
		}
	}

}
