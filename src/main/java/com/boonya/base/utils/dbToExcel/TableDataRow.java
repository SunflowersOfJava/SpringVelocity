package com.boonya.base.utils.dbToExcel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 订单导出基础类
 * 
 * @packge com.wlyd.jyxt.util.dbToExcel.TableDataRow
 * @date 2015年3月17日 下午3:58:21
 * @author shilei
 * @comment
 * @update   pengjunlin 2016年4月28日 下午15:27:50  添加注释
 */
public class TableDataRow {

	private LinkedList<TableDataCell> cells;// 表格数据列集合对象

	private TableData table;// 表格数据对象

	private int rowStyle = TableData.STYLE_TYPE_STRING;

	/**
	 * 新增一列
	 * 
	 * @MethodName: addCell
	 * @Description:
	 * @param value
	 * @throws
	 */
	public void addCell(TableDataCell cell) {
		cells.add(cell);
	}

	/**
	 * 新增一列
	 * 
	 * @MethodName: addCell
	 * @Description:
	 * @param value
	 * @throws
	 */
	public void addCell(String value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	/**
	 * 新增一列
	 * 
	 * @MethodName: addCell
	 * @Description:
	 * @param value
	 * @throws
	 */
	public void addCell(Integer value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	/**
	 * 新增一列
	 * 
	 * @MethodName: addCell
	 * @Description:
	 * @param value
	 * @throws
	 */
	public void addCell(Double value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	/**
	 * 新增一列
	 * 
	 * @MethodName: addCell
	 * @Description:
	 * @param value
	 * @throws
	 */
	public void addCell(Object value) {
		if (value instanceof String) {
			addCell((String) value);
		} else if (value instanceof Integer) {
			addCell((Integer) value);
		} else if (value instanceof Double) {
			addCell((Double) value);
		} else if (value instanceof BigDecimal) {
			addCell(value.toString());
		} else if (value instanceof Long) {
			addCell(value.toString());
		} else if (value instanceof Date) {
			addCell(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(value));
		} else if (value == null) {
			addCell("");
		}
	}

	public TableDataCell getCellAt(int index) {
		return cells.get(index);
	}

	public List<TableDataCell> getCells() {
		return cells;
	}

	public TableData getTable() {
		return table;
	}

	public TableDataRow(TableData table) {
		cells = new LinkedList<TableDataCell>();
		this.table = table;
	}

	public void setRowStyle(int rowStyle) {
		this.rowStyle = rowStyle;
	}

	public int getRowStyle() {
		return rowStyle;
	}
}
