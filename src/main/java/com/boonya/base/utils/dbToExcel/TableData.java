package com.boonya.base.utils.dbToExcel;

import java.util.LinkedList;
import java.util.List;

/**
 * 订单导出基础类
 * 
 * @packge com.wlyd.jyxt.util.dbToExcel.TableData
 * @date 2015年3月17日 下午3:56:37
 * @author shilei
 * @comment
 * @update pengjunlin 2016年4月28日 下午15:27:50 添加注释
 */
public class TableData {
	/**
	 * 字符串型
	 */
	public static final int STYLE_TYPE_STRING = 0;

	/**
	 * 浮点型，保留2位小数
	 */
	public static final int STYLE_TYPE_FLOAT_2 = 1;

	/**
	 * 浮点型，保留3位小数
	 */
	public static final int STYLE_TYPE_FLOAT_3 = 2;

	/**
	 * 整形
	 */
	public static final int STYLE_TYPE_INTEGER = 3;

	/**
	 * 红色背景
	 */
	public static final int STYLE_TYPE_RED_BG = 10;

	/**
	 * 黄色背景
	 */
	public static final int STYLE_TYPE_YELLOW_BG = 11;

	/**
	 * 绿色背景
	 */
	public static final int STYLE_TYPE_GREEN_BG = 12;

	private String sheetTitle;

	private TableHeaderMetaData header;

	private LinkedList<TableDataRow> rows;

	private int totalRows;

	/**
	 * 空构造函数
	 */
	public TableData() {
	}

	/**
	 * 设置链表行
	 * 
	 * @param header
	 */
	public TableData(TableHeaderMetaData header) {
		this.header = header;
		rows = new LinkedList<TableDataRow>();
	}

	public TableHeaderMetaData getTableHeader() {
		return header;
	}

	public void addRow(TableDataRow row) {
		rows.add(row);
	}

	public List<TableDataRow> getRows() {
		return rows;
	}

	public TableDataRow getRowAt(int index) {
		return rows.get(index);
	}

	public int getRowCount() {
		return rows.size();
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setHeader(TableHeaderMetaData header) {
		this.header = header;
	}

	public void setRows(LinkedList<TableDataRow> rows) {
		this.rows = rows;
	}

	public String getSheetTitle() {
		return sheetTitle;
	}

	public void setSheetTitle(String sheetTitle) {
		this.sheetTitle = sheetTitle;
	}

}
