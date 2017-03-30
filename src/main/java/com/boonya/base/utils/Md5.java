package com.boonya.base.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @packge com.wlyd.fmcgwms.util.Md5
 * @date 2016年4月29日 上午11:32:02
 * @author arrow
 * @comment
 * @update pengjunlin 2016年4月29日 上午11:32:02 添加注释
 */
public class Md5 {
	/**
	 * @throws Exception 
	 * 字符串MD5加密
	 * 
	 * @MethodName: getMD5Str
	 * @Description:
	 * @param str
	 * @return
	 * @throws
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if(messageDigest==null){
			try {
				throw new Exception("没有获取到加密实例对象：MessageDigest=null");// 抛出异常
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		messageDigest.reset();
		try {
			messageDigest.update(str.getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 * @throws Exception 
	 * MD5字符串添加盐值
	 * 
	 * @MethodName: getPassSalt
	 * @Description:
	 * @param key
	 * @param salt
	 * @return
	 * @throws
	 */
	public static String getPassSalt(String key, String salt) throws Exception {
		return getMD5Str(key + "{" + salt + "}");
	}
}
