package com.boonya.base.utils.dbToExcel;

import java.util.LinkedList;
import java.util.List;

/**
 * 订单导出基础类
 * 
 * @packge com.wlyd.jyxt.util.dbToExcel.TableHeaderMetaData
 * @date 2015年3月17日 下午3:58:11
 * @author shilei
 * @comment
 * @update  pengjunlin 2016年4月28日 下午15:27:50  添加注释
 */
public class TableHeaderMetaData {

	private LinkedList<TableColumn> columns;

	private LinkedList<TableColumn> leafs;

	private String common;

	public int maxlevel = 0;

	public TableHeaderMetaData() {
		columns = new LinkedList<TableColumn>();
		leafs = new LinkedList<TableColumn>();
	}

	public void addColumn(TableColumn col) {
		setLevel(col, 1);
		columns.add(col);
		addLeafColumn(col);
	}

	/**
	 * 刷新TableColumn
	 * 
	 * @MethodName: refresh
	 * @Description:
	 * @throws
	 */
	public void refresh() {
		maxlevel = 1;
		for (TableColumn col : columns) {
			if (col.isVisible()) {
				col.level = 1;
				int level = refreshChildren(col);
				if (level > maxlevel)
					maxlevel = level;
			}
		}
	}

	/**
	 * 刷新子TableColumn
	 * 
	 * @MethodName: refreshChildren
	 * @Description:
	 * @param parent
	 * @return
	 * @throws
	 */
	private int refreshChildren(TableColumn parent) {
		if (parent.children.size() != 0) {
			int max = parent.level;
			for (TableColumn col : parent.children) {
				if (col.isVisible()) {
					col.parent = parent;
					col.level = parent.level + 1;
					int level = refreshChildren(col);
					if (level > max)
						max = level;
				}
			}
			return max;
		} else {
			return parent.level;
		}
	}

	private void setLevel(TableColumn col, int level) {
		col.level = level;
		if (col.isVisible() && level > maxlevel)
			maxlevel = level;
	}

	/**
	 * 添加子叶字段
	 * 
	 * @MethodName: addLeafColumn
	 * @Description:
	 * @param col
	 * @throws
	 */
	private void addLeafColumn(TableColumn col) {
		if (col.parent != null)
			setLevel(col, col.parent.level + 1);
		if (col.isComplex()) {
			for (TableColumn c : col.getChildren()) {
				addLeafColumn(c);
			}
		} else {
			leafs.add(col);
		}
	}

	public List<TableColumn> getColumns() {
		return leafs;
	}

	/**
	 * 获取原始TableColumn集合
	 * 
	 * @MethodName: getOriginColumns
	 * @Description:
	 * @return
	 * @throws
	 */
	public List<TableColumn> getOriginColumns() {
		LinkedList<TableColumn> ret = new LinkedList<TableColumn>();
		for (TableColumn c : columns) {
			if (c.isVisible())
				ret.add(c);
		}
		return ret;
	}

	public TableColumn getColumnAt(int index) {
		return leafs.get(index);
	}

	public int getColumnCount() {
		return leafs.size();
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}
}
