package com.boonya.base.utils.base;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.boonya.base.utils.CondOrderLimit;

/**
 * mybatis基础service接口类
 * 
 * @packge com.wlyd.fmcgwms.util.base.BaseServiceImpl
 * @date 2016年4月28日 下午2:22:48
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public interface BaseService<T, PK extends java.io.Serializable> {

	// -----------mybatis自动生成代码对应的方法名---------
	/**
	 * 根据主键ID获取实体对象
	 * 
	 * @MethodName: selectByPrimaryKey
	 * @Description:
	 * @param modelPK
	 * @return
	 * @throws
	 */
	T selectByPrimaryKey(PK modelPK);

	/**
	 * 根据主键ID删除实体对象
	 * 
	 * @MethodName: deleteByPrimaryKey
	 * @Description:
	 * @param modelPK
	 * @return
	 * @throws
	 */
	Integer deleteByPrimaryKey(PK modelPK);

	/**
	 * 选择性地插入对象字段
	 * 
	 * @MethodName: insertSelective
	 * @Description:
	 * @param model
	 * @return
	 * @throws
	 */
	Integer insertSelective(T model);

	/**
	 * 插入数据对象
	 * 
	 * @MethodName: insert
	 * @Description:
	 * @param model
	 * @return
	 * @throws
	 */
	Integer insert(T model);

	/**
	 * 选择性地修改数据对象字段
	 * 
	 * @MethodName: updateByPrimaryKeySelective
	 * @Description:
	 * @param model
	 * @return
	 * @throws
	 */
	Integer updateByPrimaryKeySelective(T model);

	/**
	 * 根据主键ID修改数据对象
	 * 
	 * @MethodName: updateByPrimaryKey
	 * @Description:
	 * @param modelPK
	 * @return
	 * @throws
	 */
	Integer updateByPrimaryKey(PK modelPK);

	/**
	 * 根据条件查找对象集合
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
	 * 根据条件查询分页数据对象列表(须设置表名)
	 * 
	 * @MethodName: listPageTable
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	List<T> listPageTable(CondOrderLimit col);

	/**
	 * 根据条件查询分页数据对象列表（须设置多个表名）
	 * 
	 * @MethodName: listPageTables
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	List<T> listPageTables(CondOrderLimit col);

	/**
	 * 统计符合查询条件的对象的个数
	 * 
	 * @MethodName: count
	 * @Description:
	 * @param col
	 * @return
	 * @throws
	 */
	long count(CondOrderLimit col);

	/**
	 * 根据条件封装符合查询条件的报表字段对象数据集合
	 * 
	 * @MethodName: getExcelData
	 * @Description:
	 * @param t
	 *            实体参数对象
	 * @param sheet
	 *            sheet页对象
	 * @return
	 * @throws
	 */
	List<T> getExcelData(T t, HSSFSheet sheet);

	/**
	 * 集合对象转JsonBean集合
	 * 
	 * @MethodName: getJsonData
	 * @Description:
	 * @param list
	 * @return
	 * @throws
	 */
	List<JsonBean> getJsonData(List<T> list);
}
