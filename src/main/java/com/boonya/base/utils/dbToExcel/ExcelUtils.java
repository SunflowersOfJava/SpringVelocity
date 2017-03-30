package com.boonya.base.utils.dbToExcel;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

/**
 * 订单导出基础类
 * 
 * @packge com.wlyd.fmcgwms.util.dbToExcel.ExcelUtils
 * @date 2016年4月28日 下午2:57:39
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
@SuppressWarnings("deprecation")
public class ExcelUtils {

	/**
	 * JavaBean转Map
	 * 
	 * @MethodName: beanToMap
	 * @Description:
	 * @param obj
	 * @return
	 * @throws
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean
					.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!StringUtils.equals(name, "class")) {
					params.put(name,
							propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	/**
	 * 创建普通表头
	 * 
	 * @MethodName: createTableHeader
	 * @Description:
	 * @param list
	 *            表头名称列表
	 * @return
	 * @throws
	 */
	public static TableHeaderMetaData createTableHeader(List<String> list) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (String title : list) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(title);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建普通表头
	 * 
	 * @MethodName: createTableHeader
	 * @Description:
	 * @param titls
	 *            表头名称数组
	 * @return
	 * @throws
	 */
	public static TableHeaderMetaData createTableHeader(String[] titls) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (String title : titls) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(title);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建普通表头 + 定义每列的数值类型
	 * 
	 * @MethodName: createTableHeaderAndCellType
	 * @Description:
	 * @param titls
	 *            表头名称数组
	 * @param types
	 *            字段对应的类型数组
	 * @return
	 * @throws
	 */
	public static TableHeaderMetaData createTableHeaderAndCellType(
			String[] titls, Integer types[]) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (int i = 0; i < titls.length; i++) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(titls[i]);
			tc.setColumnType(types[i]);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建合并表头
	 * 
	 * @MethodName: createTableHeader
	 * @Description:
	 * @param parents
	 *            父表头数组
	 * @param children
	 *            子表头数组
	 * @return
	 * @throws
	 */
	public static TableHeaderMetaData createTableHeader(String[] parents,
			String[][] children) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		TableColumn parentColumn = null;
		TableColumn sonColumn = null;
		for (int i = 0; i < parents.length; i++) {
			parentColumn = new TableColumn();
			parentColumn.setDisplay(parents[i]);
			if (children != null && children[i] != null) {
				for (int j = 0; j < children[i].length; j++) {
					sonColumn = new TableColumn();
					sonColumn.setDisplay(children[i][j]);
					parentColumn.addChild(sonColumn);
				}
			}
			headMeta.addColumn(parentColumn);
		}
		return headMeta;
	}

	/**
	 * 封装TableData数据
	 * 
	 * @MethodName: createTableData
	 * @Description:
	 * @param list
	 *            数据集
	 * @param headMeta
	 *            表头
	 * @param fields
	 *            对象或Map属性数组（注意：顺序要与表头标题顺序对应，如数据集为List<Object[]>，则该参数可以为null）
	 * @return
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableData createTableData(List list,
			TableHeaderMetaData headMeta, String[] fields) {

		TableData td = new TableData(headMeta);
		TableDataRow row = null;
		if (list != null && list.size() > 0) {
			if (list.get(0).getClass().isArray()) {// 数组类型
				for (Object obj : list) {
					row = new TableDataRow(td);
					for (Object o : (Object[]) obj) {
						row.addCell(o);
					}
					td.addRow(row);
				}
			} else {// JavaBean或Map类型
				for (Object obj : list) {
					row = new TableDataRow(td);
					Map<String, Object> map = (obj instanceof Map) ? (Map<String, Object>) obj
							: beanToMap(obj);
					for (String key : fields) {
						row.addCell(map.get(key));
					}
					td.addRow(row);
				}
			}
		}
		return td;
	}

	/**
	 * 为工作表复制sheet的样式
	 * 
	 * @MethodName: copySheetStyle
	 * @Description:
	 * @param destwb
	 *            目标工作表
	 * @param dest
	 *            目标sheet
	 * @param srcwb
	 *            源工作表
	 * @param src
	 *            源sheet
	 * @throws
	 */
	public static void copySheetStyle(HSSFWorkbook destwb, HSSFSheet dest,
			HSSFWorkbook srcwb, HSSFSheet src) {
		if (src == null || dest == null)
			return;

		dest.setAlternativeExpression(src.getAlternateExpression());
		dest.setAlternativeFormula(src.getAlternateFormula());
		dest.setAutobreaks(src.getAutobreaks());
		dest.setDialog(src.getDialog());
		if (src.getColumnBreaks() != null) {
			for (int col : src.getColumnBreaks()) {
				dest.setColumnBreak((short) col);
			}
		}
		dest.setDefaultColumnWidth(src.getDefaultColumnWidth());
		dest.setDefaultRowHeight(src.getDefaultRowHeight());
		dest.setDefaultRowHeightInPoints(src.getDefaultRowHeightInPoints());
		dest.setDisplayGuts(src.getDisplayGuts());
		dest.setFitToPage(src.getFitToPage());
		dest.setHorizontallyCenter(src.getHorizontallyCenter());
		dest.setDisplayFormulas(src.isDisplayFormulas());
		dest.setDisplayGridlines(src.isDisplayGridlines());
		dest.setDisplayRowColHeadings(src.isDisplayRowColHeadings());
		dest.setGridsPrinted(src.isGridsPrinted());
		dest.setPrintGridlines(src.isPrintGridlines());

		for (int i = 0; i < src.getNumMergedRegions(); i++) {
			Region r = src.getMergedRegionAt(i);
			dest.addMergedRegion(r);
		}

		if (src.getRowBreaks() != null) {
			for (int row : src.getRowBreaks()) {
				dest.setRowBreak(row);
			}
		}
		dest.setRowSumsBelow(src.getRowSumsBelow());
		dest.setRowSumsRight(src.getRowSumsRight());

		short maxcol = 0;
		for (int i = 0; i <= src.getLastRowNum(); i++) {
			HSSFRow row = src.getRow(i);
			if (row != null) {
				if (maxcol < row.getLastCellNum())
					maxcol = row.getLastCellNum();
			}
		}
		for (short col = 0; col <= maxcol; col++) {
			if (src.getColumnWidth(col) != src.getDefaultColumnWidth())
				dest.setColumnWidth(col, src.getColumnWidth(col));
			dest.setColumnHidden(col, src.isColumnHidden(col));
		}
	}

	/**
	 * 设置报表Cell列样式
	 * 
	 * @MethodName: dumpCellStyle
	 * @Description:
	 * @param style
	 * @return
	 * @throws
	 */
	public static String dumpCellStyle(HSSFCellStyle style) {
		StringBuffer sb = new StringBuffer();
		sb.append(style.getHidden()).append(",");
		sb.append(style.getLocked()).append(",");
		sb.append(style.getWrapText()).append(",");
		sb.append(style.getAlignment()).append(",");
		sb.append(style.getBorderBottom()).append(",");
		sb.append(style.getBorderLeft()).append(",");
		sb.append(style.getBorderRight()).append(",");
		sb.append(style.getBorderTop()).append(",");
		sb.append(style.getBottomBorderColor()).append(",");
		sb.append(style.getDataFormat()).append(",");
		sb.append(style.getFillBackgroundColor()).append(",");
		sb.append(style.getFillForegroundColor()).append(",");
		sb.append(style.getFillPattern()).append(",");
		sb.append(style.getIndention()).append(",");
		sb.append(style.getLeftBorderColor()).append(",");
		sb.append(style.getRightBorderColor()).append(",");
		sb.append(style.getRotation()).append(",");
		sb.append(style.getTopBorderColor()).append(",");
		sb.append(style.getVerticalAlignment());

		return sb.toString();
	}

	/**
	 * 设置报表字体
	 * 
	 * @MethodName: dumpFont
	 * @Description:
	 * @param font
	 * @return
	 * @throws
	 */
	public static String dumpFont(HSSFFont font) {
		StringBuffer sb = new StringBuffer();
		sb.append(font.getItalic()).append(",").append(font.getStrikeout())
				.append(",").append(font.getBoldweight()).append(",")
				.append(font.getCharSet()).append(",").append(font.getColor())
				.append(",").append(font.getFontHeight()).append(",")
				.append(font.getFontName()).append(",")
				.append(font.getTypeOffset()).append(",")
				.append(font.getUnderline());
		return sb.toString();
	}

	/**
	 * 复制报表Cell样式
	 * 
	 * @MethodName: copyCellStyle
	 * @Description:
	 * @param destwb
	 * @param dest
	 * @param srcwb
	 * @param src
	 * @throws
	 */
	public static void copyCellStyle(HSSFWorkbook destwb, HSSFCell dest,
			HSSFWorkbook srcwb, HSSFCell src) {
		if (src == null || dest == null)
			return;

		HSSFCellStyle nstyle = findStyle(src.getCellStyle(), srcwb, destwb);
		if (nstyle == null) {
			nstyle = destwb.createCellStyle();
			copyCellStyle(destwb, nstyle, srcwb, src.getCellStyle());
		}
		dest.setCellStyle(nstyle);
	}

	/**
	 * 判定报表HSSFColor是否一致
	 * 
	 * @MethodName: isSameColor
	 * @Description:
	 * @param a
	 * @param b
	 * @param apalette
	 * @param bpalette
	 * @return
	 * @throws
	 */
	private static boolean isSameColor(short a, short b, HSSFPalette apalette,
			HSSFPalette bpalette) {
		if (a == b)
			return true;
		HSSFColor acolor = apalette.getColor(a);
		HSSFColor bcolor = bpalette.getColor(b);
		if (acolor == null)
			return true;
		if (bcolor == null)
			return false;
		return acolor.getHexString().equals(bcolor.getHexString());
	}

	/**
	 * 查找报表对应颜色对应的short值
	 * 
	 * @MethodName: findColor
	 * @Description:
	 * @param index
	 * @param srcwb
	 * @param destwb
	 * @return
	 * @throws
	 */
	private static short findColor(short index, HSSFWorkbook srcwb,
			HSSFWorkbook destwb) {
		Integer id = new Integer(index);
		if (HSSFColor.getIndexHash().containsKey(id))
			return index;
		if (index == HSSFColor.AUTOMATIC.index)
			return index;
		HSSFColor color = srcwb.getCustomPalette().getColor(index);
		if (color == null) {
			return index;
		}

		HSSFColor ncolor = destwb.getCustomPalette().findColor(
				(byte) color.getTriplet()[0], (byte) color.getTriplet()[1],
				(byte) color.getTriplet()[2]);
		if (ncolor != null)
			return ncolor.getIndex();
		destwb.getCustomPalette().setColorAtIndex(index,
				(byte) color.getTriplet()[0], (byte) color.getTriplet()[1],
				(byte) color.getTriplet()[2]);
		return index;
	}

	/**
	 * 查找报表样式
	 * 
	 * @MethodName: findStyle
	 * @Description:
	 * @param style
	 * @param srcwb
	 * @param destwb
	 * @return
	 * @throws
	 */
	public static HSSFCellStyle findStyle(HSSFCellStyle style,
			HSSFWorkbook srcwb, HSSFWorkbook destwb) {
		HSSFPalette srcpalette = srcwb.getCustomPalette();
		HSSFPalette destpalette = destwb.getCustomPalette();

		for (short i = 0; i < destwb.getNumCellStyles(); i++) {
			HSSFCellStyle old = destwb.getCellStyleAt(i);
			if (old == null)
				continue;

			if (style.getAlignment() == old.getAlignment()
					&& style.getBorderBottom() == old.getBorderBottom()
					&& style.getBorderLeft() == old.getBorderLeft()
					&& style.getBorderRight() == old.getBorderRight()
					&& style.getBorderTop() == old.getBorderTop()
					&& isSameColor(style.getBottomBorderColor(),
							old.getBottomBorderColor(), srcpalette, destpalette)
					&& style.getDataFormat() == old.getDataFormat()
					&& isSameColor(style.getFillBackgroundColor(),
							old.getFillBackgroundColor(), srcpalette,
							destpalette)
					&& isSameColor(style.getFillForegroundColor(),
							old.getFillForegroundColor(), srcpalette,
							destpalette)
					&& style.getFillPattern() == old.getFillPattern()
					&& style.getHidden() == old.getHidden()
					&& style.getIndention() == old.getIndention()
					&& isSameColor(style.getLeftBorderColor(),
							old.getLeftBorderColor(), srcpalette, destpalette)
					&& style.getLocked() == old.getLocked()
					&& isSameColor(style.getRightBorderColor(),
							old.getRightBorderColor(), srcpalette, destpalette)
					&& style.getRotation() == old.getRotation()
					&& isSameColor(style.getTopBorderColor(),
							old.getTopBorderColor(), srcpalette, destpalette)
					&& style.getVerticalAlignment() == old
							.getVerticalAlignment()
					&& style.getWrapText() == old.getWrapText()) {

				HSSFFont oldfont = destwb.getFontAt(old.getFontIndex());
				HSSFFont font = srcwb.getFontAt(style.getFontIndex());
				if (oldfont.getBoldweight() == font.getBoldweight()
						&& oldfont.getItalic() == font.getItalic()
						&& oldfont.getStrikeout() == font.getStrikeout()
						&& oldfont.getCharSet() == font.getCharSet()
						&& isSameColor(oldfont.getColor(), font.getColor(),
								srcpalette, destpalette)
						&& oldfont.getFontHeight() == font.getFontHeight()
						&& oldfont.getFontName().equals(font.getFontName())
						&& oldfont.getTypeOffset() == font.getTypeOffset()
						&& oldfont.getUnderline() == font.getUnderline()) {
					return old;
				}
			}
		}
		return null;
	}

	/**
	 * 复制报表Cell样式
	 * 
	 * @MethodName: copyCellStyle
	 * @Description:
	 * @param destwb
	 * @param dest
	 * @param srcwb
	 * @param src
	 * @throws
	 */
	public static void copyCellStyle(HSSFWorkbook destwb, HSSFCellStyle dest,
			HSSFWorkbook srcwb, HSSFCellStyle src) {
		if (src == null || dest == null)
			return;
		dest.setAlignment(src.getAlignment());
		dest.setBorderBottom(src.getBorderBottom());
		dest.setBorderLeft(src.getBorderLeft());
		dest.setBorderRight(src.getBorderRight());
		dest.setBorderTop(src.getBorderTop());
		dest.setBottomBorderColor(findColor(src.getBottomBorderColor(), srcwb,
				destwb));
		dest.setDataFormat(destwb.createDataFormat().getFormat(
				srcwb.createDataFormat().getFormat(src.getDataFormat())));
		dest.setFillPattern(src.getFillPattern());
		dest.setFillForegroundColor(findColor(src.getFillForegroundColor(),
				srcwb, destwb));
		dest.setFillBackgroundColor(findColor(src.getFillBackgroundColor(),
				srcwb, destwb));
		dest.setHidden(src.getHidden());
		dest.setIndention(src.getIndention());
		dest.setLeftBorderColor(findColor(src.getLeftBorderColor(), srcwb,
				destwb));
		dest.setLocked(src.getLocked());
		dest.setRightBorderColor(findColor(src.getRightBorderColor(), srcwb,
				destwb));
		dest.setRotation(src.getRotation());
		dest.setTopBorderColor(findColor(src.getTopBorderColor(), srcwb, destwb));
		dest.setVerticalAlignment(src.getVerticalAlignment());
		dest.setWrapText(src.getWrapText());

		HSSFFont f = srcwb.getFontAt(src.getFontIndex());
		HSSFFont nf = findFont(f, srcwb, destwb);
		if (nf == null) {
			nf = destwb.createFont();
			nf.setBoldweight(f.getBoldweight());
			nf.setCharSet(f.getCharSet());
			nf.setColor(findColor(f.getColor(), srcwb, destwb));
			nf.setFontHeight(f.getFontHeight());
			nf.setFontHeightInPoints(f.getFontHeightInPoints());
			nf.setFontName(f.getFontName());
			nf.setItalic(f.getItalic());
			nf.setStrikeout(f.getStrikeout());
			nf.setTypeOffset(f.getTypeOffset());
			nf.setUnderline(f.getUnderline());
		}
		dest.setFont(nf);
	}

	/**
	 * 查找报表字体对象HSSFFont
	 * 
	 * @MethodName: findFont
	 * @Description:
	 * @param font
	 * @param src
	 * @param dest
	 * @return
	 * @throws
	 */
	private static HSSFFont findFont(HSSFFont font, HSSFWorkbook src,
			HSSFWorkbook dest) {
		for (short i = 0; i < dest.getNumberOfFonts(); i++) {
			HSSFFont oldfont = dest.getFontAt(i);
			if (font.getBoldweight() == oldfont.getBoldweight()
					&& font.getItalic() == oldfont.getItalic()
					&& font.getStrikeout() == oldfont.getStrikeout()
					&& font.getCharSet() == oldfont.getCharSet()
					&& font.getColor() == oldfont.getColor()
					&& font.getFontHeight() == oldfont.getFontHeight()
					&& font.getFontName().equals(oldfont.getFontName())
					&& font.getTypeOffset() == oldfont.getTypeOffset()
					&& font.getUnderline() == oldfont.getUnderline()) {
				return oldfont;
			}
		}
		return null;
	}

	/**
	 * 复制一个sheet HSSFSheet
	 * 
	 * @MethodName: copySheet
	 * @Description:
	 * @param destwb
	 *            目标工作簿
	 * @param dest
	 *            目标sheet
	 * @param srcwb
	 *            源工作簿
	 * @param src
	 *            源sheet
	 * @throws
	 */
	public static void copySheet(HSSFWorkbook destwb, HSSFSheet dest,
			HSSFWorkbook srcwb, HSSFSheet src) {
		if (src == null || dest == null)
			return;

		copySheetStyle(destwb, dest, srcwb, src);

		for (int i = 0; i <= src.getLastRowNum(); i++) {
			HSSFRow row = src.getRow(i);
			copyRow(destwb, dest.createRow(i), srcwb, row);
		}
	}

	/**
	 * 复制报表的行HSSFRow
	 * 
	 * @MethodName: copyRow
	 * @Description:
	 * @param destwb
	 *            目标工作簿
	 * @param dest
	 *            目标行
	 * @param srcwb
	 *            源工作簿
	 * @param src
	 *            源行
	 * @throws
	 */
	public static void copyRow(HSSFWorkbook destwb, HSSFRow dest,
			HSSFWorkbook srcwb, HSSFRow src) {
		if (src == null || dest == null)
			return;
		for (short i = 0; i <= src.getLastCellNum(); i++) {
			if (src.getCell(i) != null) {
				HSSFCell cell = dest.createCell(i);
				copyCell(destwb, cell, srcwb, src.getCell(i));
			}
		}

	}

	/**
	 * 复制报表的一个Cell HSSFCell
	 * 
	 * @MethodName: copyCell
	 * @Description:
	 * @param destwb
	 *            目标工作簿
	 * @param dest
	 *            目标Cell
	 * @param srcwb
	 *            源工作簿
	 * @param src
	 *            源Cell
	 * @throws
	 */
	public static void copyCell(HSSFWorkbook destwb, HSSFCell dest,
			HSSFWorkbook srcwb, HSSFCell src) {
		if (src == null) {
			dest.setCellType(HSSFCell.CELL_TYPE_BLANK);
			return;
		}

		if (src.getCellComment() != null)
			dest.setCellComment(src.getCellComment());
		if (src.getCellStyle() != null) {
			HSSFCellStyle nstyle = findStyle(src.getCellStyle(), srcwb, destwb);
			if (nstyle == null) {
				nstyle = destwb.createCellStyle();
				copyCellStyle(destwb, nstyle, srcwb, src.getCellStyle());
			}
			dest.setCellStyle(nstyle);
		}
		dest.setCellType(src.getCellType());

		switch (src.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:

			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			dest.setCellValue(src.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			dest.setCellFormula(src.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			dest.setCellErrorValue(src.getErrorCellValue());
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			dest.setCellValue(src.getNumericCellValue());
			break;
		default:
			dest.setCellValue(new HSSFRichTextString(src
					.getRichStringCellValue().getString()));
			break;
		}
	}
}
