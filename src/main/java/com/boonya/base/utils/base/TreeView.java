package com.boonya.base.utils.base;

import java.io.Serializable;
import java.util.List;

/**
 * （所有树形菜单view基类）封装系统功能及其子功能树视图类
 * 
 * @packge com.wlyd.fmcgwms.util.base.TreeView
 * @date 2016年4月28日 下午2:34:52
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public class TreeView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;// 功能ID

	private String text;// 功能名称

	private String state;// 节点打开或关闭状态

	private String codePath;// 编码路径

	private Integer parentId;// 父节点

	private boolean hasMenu = false;// 验证是否有权限

	private List<Object> listTree;// 子节点集合

	private String namePath;// 名称路径

	private boolean checked = false;// 是否选中

	private String iconCls;// 图标CLS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public boolean isHasMenu() {
		return hasMenu;
	}

	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}

	public List<Object> getListTree() {
		return listTree;
	}

	public void setListTree(List<Object> listTree) {
		this.listTree = listTree;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
