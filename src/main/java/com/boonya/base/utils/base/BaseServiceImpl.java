package com.boonya.base.utils.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.boonya.base.utils.CondOrderLimit;
import com.boonya.base.utils.Tools;

/**
 * mybatis基础service实现类
 * 
 * @packge com.wlyd.fmcgwms.util.base.BaseServiceImpl
 * @date 2016年4月28日 下午2:22:48
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public class BaseServiceImpl<T, PK extends java.io.Serializable> implements
		BaseService<T, PK> {
	protected BaseMapper<T, PK> dao;

	@Override
	public long count(CondOrderLimit col) {
		return dao.count(col);
	}

	@Override
	public Integer deleteByPrimaryKey(PK modelPK) {

		return dao.deleteByPrimaryKey(modelPK);
	}

	@Override
	public List<T> find(CondOrderLimit col) {
		return dao.find(col);
	}

	@Override
	public Integer insert(T model) {
		return dao.insert(model);
	}

	@Override
	public Integer insertSelective(T model) {
		return dao.insertSelective(model);
	}

	@Override
	public List<T> listPage(CondOrderLimit col) {
		Map<String, Object> describe = col.getConditions();
		Set<String> sd = describe.keySet();
		for (String string : sd) {
			Object object = describe.get(string);
			if (object instanceof String) {
				String value = (String) object;
				value = value.replace("'", "");
				describe.put(string, value);
			}
		}
		col.putConditions(describe);
		return dao.listPage(col);
	}

	@Override
	public List<T> listPageTable(CondOrderLimit col) {
		Map<String, Object> describe = col.getConditions();
		Set<String> sd = describe.keySet();
		for (String string : sd) {
			Object object = describe.get(string);
			if (object instanceof String) {
				String value = (String) object;
				value = value.replace("'", "");
				describe.put(string, value);
			}
		}
		col.putConditions(describe);
		return dao.listPageTable(col);
	}

	@Override
	public List<T> listPageTables(CondOrderLimit col) {
		Map<String, Object> describe = col.getConditions();
		Set<String> sd = describe.keySet();
		for (String string : sd) {
			Object object = describe.get(string);
			if (object instanceof String) {
				String value = (String) object;
				value = value.replace("'", "");
				describe.put(string, value);
			}
		}
		col.putConditions(describe);
		return dao.listPageTables(col);
	}

	@Override
	public T selectByPrimaryKey(PK modelPK) {
		return dao.selectByPrimaryKey(modelPK);
	}

	@Override
	public Integer updateByPrimaryKey(PK modelPK) {
		return dao.updateByPrimaryKey(modelPK);
	}

	@Override
	public Integer updateByPrimaryKeySelective(T model) {
		return dao.updateByPrimaryKeySelective(model);
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	@Override
	public List<T> getExcelData(T t, HSSFSheet sheet) {
		// TODO Auto-generated method stub
		Iterator<Row> rows = sheet.rowIterator();
		List<T> ts = new ArrayList<T>();
		// 循环行
		a: while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();
			if (row.getRowNum() >= 4) {
				Iterator<Cell> cells = row.cellIterator();
				// 循环列
				while (cells.hasNext()) {
					HSSFCell cell = (HSSFCell) cells.next();
					int col = cell.getCellNum();
					switch (col) {
					// json
					case 0:
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							if (!"".equals(cell.getStringCellValue())
									&& cell.getStringCellValue() != null
									&& (Tools.jsonToObj(
											cell.getStringCellValue(),
											t.getClass()) != null)) {
								t = (T) Tools
										.jsonToObj(cell.getStringCellValue(),
												t.getClass());
							}
						}
					}
				}
				if (t != null) {
					ts.add(t);
				}
			}
		}
		return ts;
	}

	@Override
	public List<JsonBean> getJsonData(List<T> list) {
		List<JsonBean> jsonBeans = new ArrayList<JsonBean>();
		if (list != null) {
			for (T t : list) {
				JsonBean jsonBean = new JsonBean();
				jsonBean.setJson(Tools.toJson(t));
				jsonBeans.add(jsonBean);
			}
		}
		return jsonBeans;
	}
}
