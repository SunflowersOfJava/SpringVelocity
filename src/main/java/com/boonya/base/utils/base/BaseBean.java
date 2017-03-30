package com.boonya.base.utils.base;

import java.io.Serializable;
import java.util.Date;
/**
 * mybatis BaseBean类
 * 
 * @packge com.wlyd.fmcgwms.util.base.BaseBean
 * @date 2016年4月28日 下午2:32:41
 * @author pengjunlin
 * @comment 添加注释
 * @update
 */
public class BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1727605156609983257L;

	private Integer createUser;// 创建人

	private String createDate;// 创建时间

	private Integer updateUser;// 更新人

	private String updateDate;// 更新时间

	private String lastIp;// 最后登录ip

	private String lastAddress;// 最后登录地址

	private String lastLoginDate;// 最后登录时间

	private Integer disabled;

	private Date modifyTime;// 修改时间

	private Integer modifier;// 修改人

	private Date createTime;// 创建时间

	private Integer creator;// 创建人

	private String strModifyTime;// 修改日期格式化
	
	private String strCreateTime;// 创建日期格式化

	private String modifierName;// 修改人名称
	private String creatorName;// 创建人名称
	private String esCorCode;// 组织编码

	
	public String getModifierName() {
		return modifierName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public String getLastAddress() {
		return lastAddress;
	}

	public void setLastAddress(String lastAddress) {
		this.lastAddress = lastAddress;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getEsCorCode() {
		return esCorCode;
	}

	public void setEsCorCode(String esCorCode) {
		this.esCorCode = esCorCode;
	}

	public String getStrModifyTime() {
		return strModifyTime;
	}

	public void setStrModifyTime(String strModifyTime) {
		this.strModifyTime = strModifyTime;
	}

	public String getStrCreateTime() {
		return strCreateTime;
	}

	public void setStrCreateTime(String strCreateTime) {
		this.strCreateTime = strCreateTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	

}
