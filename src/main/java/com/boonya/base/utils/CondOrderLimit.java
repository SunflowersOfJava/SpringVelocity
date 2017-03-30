package com.boonya.base.utils;

import java.util.HashMap;
import java.util.Map;
import com.boonya.base.utils.mybatis.Page;

/**
 * 类描述：查询辅助类，支持链式写法，如：new
 * CondOrderLimit().setOrderBy("id desc").setLimit(5).putCondition
 * ("username","fgjun").putCondition("age",33)
 * 
 * @packge com.wlyd.fmcgwms.util.CondOrderLimit
 * @date 2014年3月16日 下午11:30:31
 * @author fgjun
 * @comment
 * @update pengjunlin 2016年4月29日 上午10:01:40 添加注释，代码重构
 */
public class CondOrderLimit {

	private Map<String, Object> conditions = new HashMap<String, Object>();// 条件

	private String orderBy;// 排序

	private String limit;// 限制结果数

	private Page paper;// 分页

	private String groupBy;// 分组查询

	private String tableName;// 表名

	private Map<String, String> tables = new HashMap<String, String>();// 多表名情况

	/**
	 * 构造函数(无参空构造函数)
	 */
	public CondOrderLimit() {
	}

	/**
	 * 构造函数(条件查询)
	 * 
	 * @param conditions
	 */
	public CondOrderLimit(Map<String, Object> conditions) {
		this.conditions = conditions;
	}

	/**
	 * 构造函数(排序查询)
	 * 
	 * @param conditions
	 * @param orderBy
	 */
	public CondOrderLimit(Map<String, Object> conditions, String orderBy) {
		this.conditions = conditions;
		this.orderBy = orderBy;
	}

	/**
	 * 构造函数(分页查询)
	 * 
	 * @param conditions
	 * @param limit
	 */
	public CondOrderLimit(Map<String, Object> conditions, Integer limit) {
		this.conditions = conditions;
		this.limit = limit + "";
	}

	/**
	 * 构造函数(分页排序查询)
	 * 
	 * @param conditions
	 * @param orderBy
	 * @param limit
	 */
	public CondOrderLimit(Map<String, Object> conditions, String orderBy,
			Integer limit) {
		this.conditions = conditions;
		this.orderBy = orderBy;
		this.limit = limit + "";
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public CondOrderLimit putConditions(Map<String, Object> conditions) {
		this.conditions.putAll(conditions);
		return this;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public CondOrderLimit setOrderBy(String orderBy) {
		// if (Tools.isEmpty(this.orderBy)) {
		this.orderBy = orderBy;
		// }
		return this;
	}

	public String getLimit() {
		return limit;
	}

	public CondOrderLimit setLimit(Integer limit) {
		this.limit = limit + "";
		return this;
	}

	/**
	 * 方法描述：如:0,7
	 * 
	 * @MethodName: setLimit
	 * @Description:
	 * @param page
	 * @param limit
	 * @return
	 * @version: 1.0
	 * @author: fgjun
	 * @version: 2014-4-9 下午02:55:32
	 * @throws
	 */
	public CondOrderLimit setLimit(Integer page, Integer limit) {
		this.limit = page + "," + limit;
		return this;
	}

	public Page getPaper() {
		return paper;
	}

	public CondOrderLimit setPaper(Page paper) {
		this.paper = paper;
		return this;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public CondOrderLimit setGroupBy(String groupBy) {
		this.groupBy = groupBy;
		return this;
	}

	public CondOrderLimit setPageRows(Integer rows) {
		this.paper.setRows(rows);
		return this;
	}

	/**
	 * 
	 * 方法描述：清空所有查询条件
	 * 
	 * @MethodName: clearCondtions
	 * @Description:
	 * @param:
	 * @return:
	 * @version: 1.0
	 * @author: fgjun
	 * @version: 2014-3-16 下午11:29:20
	 */
	public CondOrderLimit clearCondtions() {
		this.conditions.clear();
		return this;
	}

	/**
	 * 
	 * 方法描述：重置
	 * 
	 * @MethodName: reset
	 * @Description:
	 * @param:
	 * @return:
	 * @version: 1.0
	 * @author: fgjun
	 * @version: 2014-3-16 下午11:29:06
	 */
	public CondOrderLimit reset() {
		this.conditions.clear();
		this.groupBy = null;
		this.orderBy = null;
		this.limit = null;
		return this;
	}

	/**
	 * 
	 * 方法描述：放入一个查询条件
	 * 
	 * @MethodName: putCondition
	 * @Description:
	 * @param:
	 * @return:
	 * @version: 1.0
	 * @author: fgjun
	 * @version: 2014-3-16 下午11:29:43
	 */
	public CondOrderLimit putCondition(String key, Object value) {
		this.conditions.put(key, value);
		return this;
	}

	/**
	 * 
	 * 方法描述：移除一个查询条件
	 * 
	 * @MethodName: removeCondition
	 * @Description:
	 * @param:
	 * @return:
	 * @version: 1.0
	 * @author: fgjun
	 * @version: 2014-3-16 下午11:29:59
	 */
	public CondOrderLimit removeCondition(String key) {
		this.conditions.remove(key);
		return this;
	}

	/**
	 * 
	 * 方法描述：取得某个条件的值
	 * 
	 * @MethodName: getCondition
	 * @Description:
	 * @param:
	 * @return:
	 * @version: 1.0
	 * @author: fgjun
	 * @version: 2014-3-19 上午12:06:23
	 */
	public Object getCondition(String key) {
		return this.conditions.get(key);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public CondOrderLimit putTables(String key, String value) {
		this.tables.put(key, value);
		return this;
	}

	public CondOrderLimit removeTables(String key) {
		this.tables.remove(key);
		return this;
	}

	public Object getTables(String key) {
		return this.tables.get(key);
	}
}