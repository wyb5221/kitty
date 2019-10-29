package com.louis.kitty.admin.model;

/**
 * ---------------------------
 * 角色菜单 (SysMenuApi)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-29 10:16:45
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class SysMenuApi {

	/** 编号 */
	private Long id;
	/** 菜单ID */
	private Long menuKey;
	/** api表ID */
	private Long apiKey;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新人 */
	private String lastUpdateBy;
	/** 更新时间 */
	private java.util.Date lastUpdateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(Long menuKey) {
		this.menuKey = menuKey;
	}

	public Long getApiKey() {
		return apiKey;
	}

	public void setApiKey(Long apiKey) {
		this.apiKey = apiKey;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public java.util.Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(java.util.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}