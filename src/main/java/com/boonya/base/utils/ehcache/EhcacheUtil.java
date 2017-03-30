package com.boonya.base.utils.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ehcache 缓存工具类
 * 
 * @packge com.wlyd.fmcgwms.util.ehcache.EhcacheUtil
 * @date 2016年4月28日 下午4:11:27
 * @author pengjunlin
 * @comment cacheName在ehcache.xml中配置
 * @update 添加注释，代码重构删除原有类com。wlyd.fmcgwms.util.ehcache.EhcacheUtilOverWrite
 */
public class EhcacheUtil {

	public static CacheManager manager = CacheManager.create();// 缓存管理

	public static String cacheName = "metaCache";// 缓存名称

	/**
	 * 获取缓存对象
	 * 
	 * @MethodName: get
	 * @Description:
	 * @param key
	 * @return
	 * @throws
	 */
	public static Object get(Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
		}
		return null;
	}

	/**
	 * 获取缓存对象
	 * 
	 * @MethodName: get
	 * @Description:
	 * @param cacheName
	 * @param key
	 * @return
	 * @throws
	 */
	public static Object get(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
		}
		return null;
	}

	/**
	 * 添加缓存对象
	 * 
	 * @MethodName: put
	 * @Description:
	 * @param key
	 * @param value
	 * @throws
	 */
	public static void put(Object key, Object value) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(new Element(key, value));
		}
	}

	/**
	 * 添加缓存对象
	 * 
	 * @MethodName: put
	 * @Description:
	 * @param cacheName
	 * @param key
	 * @param value
	 * @throws
	 */
	public static void put(String cacheName, Object key, Object value) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(new Element(key, value));
		}
	}

	/**
	 * 移出缓存对象
	 * 
	 * @MethodName: remove
	 * @Description:
	 * @param key
	 * @return
	 * @throws
	 */
	public static boolean remove(Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			return cache.remove(key);
		}
		return false;
	}

	/**
	 * 移除缓存对象
	 * 
	 * @MethodName: remove
	 * @Description:
	 * @param cacheName
	 * @param key
	 * @return
	 * @throws
	 */
	public static boolean remove(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			return cache.remove(key);
		}
		return false;
	}
}