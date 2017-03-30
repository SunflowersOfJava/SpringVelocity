package com.boonya.base.utils.finals;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 * 系统常量配置类
 * 
 * @packge com.wlyd.fmcgwms.util.finals.Const
 * @date 2016年4月28日 下午4:48:53
 * @author arrow
 * @comment
 * @update pengjunlin 2016年4月28日 下午4:48:53 添加注释
 */
public class Const {

	/**
	 * 后台账户
	 */
	public static final String SESSION_BACK_USER = "sessionBackUser";
	/**
	 * 验证码
	 */
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	/**
	 * 不对匹配该值的访问路径拦截
	 */
	public static final String NO_INTERCEPTOR_PATH = "NO_INTERCEPTOR_PATH";

	/**
	 * 网站安全秘钥
	 */
	public static final String WEB_SECURE_KEY = "!#&*990abYwd3q_@!#_+&^$45&*";

	/**
	 * 用户登录token
	 */
	public static final String AUTO_LOGIN_COOKIE_NAME = "login_token";

	/**
	 * spring容器
	 */
	public static ApplicationContext APPLICATION_CONTEXT;
	/**
	 * sessionUser会话
	 */
	public static final Map<String, String> sessionUser = new HashMap<String, String>();
}