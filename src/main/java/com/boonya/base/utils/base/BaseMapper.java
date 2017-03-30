package com.boonya.base.utils.base;

import java.util.List;

import com.boonya.base.utils.CondOrderLimit;

/**
 * mybatis基础mapper接口类
 * 
 * @packge com.wlyd.fmcgwms.util.base.BaseMapper
 * @date 2016年4月28日 下午2:22:02
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public interface BaseMapper<T, PK extends java.io.Serializable> {

	// -----------mybatis自动生成代码对应的方法名---------
	/**
	 * 根据实体主键ID查询对象
	 * 
	 * @MethodName: selectByPrimaryKey
	 * @Description:
	 * @param modelPK
	 * @return
	 * @throws
	 */
	T selectByPrimaryKey(PK modelPK);

	/**
	 * 根据实体主键ID删除对象
	 * 
	 * @MethodName: deleteByPrimaryKey
	 * @Description:
	 * @param modelPK
	 * @return
	 * @throws
	 */
	Integer deleteByPrimaryKey(PK modelPK);

	/**
	 * 选择性地插入实体对象
	 * 
	 * @MethodName: insertSelective
	 * @Description:
	 * @param model
	 * @return
	 * @throws
	 */
	Integer insertSelective(T model);

	/**
	 * 插入实体对象
	 * 
	 * @MethodName: insert
	 * @Description:
	 * @param model
	 * @return
	 * @throws
	 */
	Integer insert(T model);

	/**
	 * 选择性地更新实体对象
	 * 
	 * @MethodName: updateByPrimaryKeySelective
	 * @Description:
	 * @param model
	 * @return
	 * @throws
	 */
	Integer updateByPrimaryKeySelective(T model);

	/**
	 * 根据主键更新实体对象
	 * 
	 * @MethodName: updateByPrimaryKey
	 * @Description:
	 * @param modelPK
	 * @return
	 * @throws
	 */
	Integer updateByPrimaryKey(PK modelPK);

	/**
	 * 根据条件查询数据对象列表
	 * 
	 * @MethodName: find
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	List<T> find(CondOrderLimit col);

	/**
	 * 根据条件查询分页数据对象列表
	 * 
	 * @MethodName: listPage
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	List<T> listPage(CondOrderLimit col);

	/**
	 * 根据条件查询分页数据对象列表(设置表名)
	 * 
	 * @MethodName: listPageTable
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	List<T> listPageTable(CondOrderLimit col);

	/**
	 * 根据条件查询分页数据对象列表(设置多个表名)
	 * 
	 * @MethodName: listPageTables
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	List<T> listPageTables(CondOrderLimit col);

	/**
	 * 根据条件统计符合条件的实体对象个数
	 * 
	 * @MethodName: count
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	long count(CondOrderLimit col);

	/**
	 * 查询报表返回数据对象集合
	 * 
	 * @MethodName: findReports
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	List<T> findReports(CondOrderLimit col);
}
