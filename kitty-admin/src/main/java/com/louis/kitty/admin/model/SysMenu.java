package com.louis.kitty.admin.model;

import java.util.List;

/**
 * ---------------------------
 * 菜单管理 (SysMenu)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:25
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class SysMenu extends BaseModel {

	/** 系统表主键 */
	private Long sysKey;
	/** 菜单名称 */
	private String name;
	/** 父菜单ID，一级菜单为0 */
	private Long parentId;
	/** 菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址) */
	private String url;
	/** 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit) */
	private String perms;
	/** 类型   0：目录   1：菜单   2：按钮 */
	private Integer type;
	/** 菜单图标 */
	private String icon;
	/** 排序 */
	private Integer orderNum;
	/** 是否删除  -1：已删除  0：正常 */
	private Integer delFlag;
	/** 是否是系统默认菜单 */
	private String sysDefault;

	// 非数据库字段
	private String parentName;
	// 非数据库字段
	private Integer level;
	// 非数据库字段
	private List<SysMenu> children;

	public Long getSysKey() {
		return sysKey;
	}

	public void setSysKey(Long sysKey) {
		this.sysKey = sysKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public void setSysDefault(String sysDefault) {
		this.sysDefault = sysDefault;
	}

	public String getSysDefault() {

		return sysDefault;
	}
}