package com.louis.kitty.admin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------
 * 用户管理 (SysUser)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:25
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class SysUser extends BaseModel {

	/** 用户名 */
	private String name;
	/** 登录账号 */
	private String userName;
	/** 密码 */
	private String password;
	/** 盐 */
	private String salt;
	/** 邮箱 */
	private String email;
	/** 手机号 */
	private String mobile;
	/** 状态  0：禁用   1：正常 */
	private Integer status;
	/** 机构ID */
	private Long deptId;
	/** 是否删除  -1：已删除  0：正常 */
	private Integer delFlag;

	private String roleNames;

	private List<SysUserRole> userRoles = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return userName;
	}

	public void setLoginName(String loginName) {
		this.userName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public List<SysUserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<SysUserRole> userRoles) {
		this.userRoles = userRoles;
	}
}