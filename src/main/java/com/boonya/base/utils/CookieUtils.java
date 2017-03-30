package com.boonya.base.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 * 
 * @packge com.wlyd.fmcgwms.util.CookieUtils
 * @date 2016年4月29日 上午10:55:11
 * @author pengjunlin
 * @comment
 * @update 添加注释
 */
public class CookieUtils {

	/**
	 * 获取名称对应的Cookie串
	 * 
	 * @MethodName: getCookieValue
	 * @Description:
	 * @param request
	 * @param name
	 * @return
	 * @throws
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		try {
			return cookie == null ? null : URLDecoder.decode(cookie.getValue(),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取名称对应的解密Cookie串
	 * 
	 * @MethodName: getCookieValueDecrypt
	 * @Description:
	 * @param request
	 * @param name
	 * @return
	 * @throws
	 */
	public static String getCookieValueDecrypt(HttpServletRequest request,
			String name) {
		Cookie cookie = getCookie(request, name);
		return cookie == null ? null : DesUtils.decrypt(cookie.getValue());
	}

	/**
	 * 获取名称对应的Cookie对象
	 * 
	 * @MethodName: getCookie
	 * @Description:
	 * @param request
	 * @param name
	 * @return
	 * @throws
	 */
	private static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 创建Cookie
	 * 
	 * @MethodName: createCookie
	 * @Description:
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param httpOnly
	 * @param isEncrypt
	 * @return
	 * @throws
	 */
	private static Cookie createCookie(String name, String value, int maxAge,
			boolean httpOnly, boolean isEncrypt) {
		if (isEncrypt) {
			value = DesUtils.encrypt(value);
		}
		Cookie cookie = new Cookie(name, value);
		// cookie.setPath("/");
		cookie.setMaxAge(maxAge * 24 * 60 * 60);
		cookie.setHttpOnly(httpOnly);
		return cookie;
	}

	/**
	 * 设置响应浏览器/客户端的Cookie
	 * 
	 * @MethodName: addCookie
	 * @Description:
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @throws
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		Cookie cookie = createCookie(name, value, maxAge, false, false);
		response.addCookie(cookie);
	}

	/**
	 * 设置响应浏览器/客户端的解密Cookie
	 * 
	 * @MethodName: addCookieEncrypt
	 * @Description:
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @throws
	 */
	public static void addCookieEncrypt(HttpServletResponse response,
			String name, String value, int maxAge) {
		Cookie cookie = createCookie(name, value, maxAge, false, true);
		response.addCookie(cookie);
	}

	/**
	 * 设置响应浏览器/客户端的Cookie
	 * 
	 * @MethodName: addCookie
	 * @Description:
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param httpOnly
	 * @throws
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge, boolean httpOnly) {
		Cookie cookie = createCookie(name, value, maxAge, true, false);
		response.addCookie(cookie);
	}

	/**
	 * 设置响应浏览器/客户端的解密Cookie
	 * 
	 * @MethodName: addCookieEncrypt
	 * @Description:
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param httpOnly
	 * @throws
	 */
	public static void addCookieEncrypt(HttpServletResponse response,
			String name, String value, int maxAge, boolean httpOnly) {
		Cookie cookie = createCookie(name, value, maxAge, true, true);
		response.addCookie(cookie);
	}

	/**
	 * 从浏览器/客户端移除Cookie
	 * 
	 * @MethodName: removeCookie
	 * @Description:
	 * @param request
	 * @param response
	 * @param name
	 * @throws
	 */
	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
}
