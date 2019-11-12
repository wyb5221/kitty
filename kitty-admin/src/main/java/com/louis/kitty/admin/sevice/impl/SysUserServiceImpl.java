package com.louis.kitty.admin.sevice.impl;

import com.louis.kitty.admin.dao.SysRoleMapper;
import com.louis.kitty.admin.dao.SysUserMapper;
import com.louis.kitty.admin.dao.SysUserRoleMapper;
import com.louis.kitty.admin.enums.UserStatusEnum;
import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.admin.model.SysRole;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.model.SysUserRole;
import com.louis.kitty.admin.sevice.SysMenuService;
import com.louis.kitty.admin.sevice.SysUserService;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SysUserServiceImpl  implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Value("${login_error_max_time}")
	private String maxTime;


	@Transactional
	@Override
	public int save(SysUser record) {
		Long id = null;
		if(record.getId() == null || record.getId() == 0) {
			// 新增用户
			sysUserMapper.insertSelective(record);
			id = record.getId();
		} else {
			// 更新用户信息
			sysUserMapper.updateByPrimaryKeySelective(record);
		}
		// 更新用户角色
		if(id != null && id == 0) {
			return 1;
		}
		if(id != null) {
			for(SysUserRole sysUserRole:record.getUserRoles()) {
				sysUserRole.setUserId(id);
			}
		} else {
			sysUserRoleMapper.deleteByUserId(record.getId());
		}
		for(SysUserRole sysUserRole:record.getUserRoles()) {
			sysUserRoleMapper.insertSelective(sysUserRole);
		}
		return 1;
	}

	@Override
	public int delete(SysUser record) {
		return sysUserMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysUser> records) {
		for(SysUser record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysUser findById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(SysUser record) {
		return 0;
	}

	@Override
	public SysUser findByName(String name) {
		return sysUserMapper.findByName(name);
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		String name = getColumnFilterValue(pageRequest, "name");
		String email = getColumnFilterValue(pageRequest, "email");
		if(name != null) {
			if(email != null) {
				pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByNameAndEmail", name, email);
			} else {
				pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByName", name);
			}
		} else {
			pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper);
		}
		// 加载用户角色信息
		findUserRoles(pageResult);
		return pageResult;
	}

	/**
	 * 获取过滤字段的值
	 * @param filterName
	 * @return
	 */
	public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		if(columnFilter != null) {
			value = columnFilter.getValue();
		}
		return value;
	}
	
	/**
	 * 加载用户角色
	 * @param pageResult
	 */
	private void findUserRoles(PageResult pageResult) {
		List<?> content = pageResult.getContent();
		for(Object object:content) {
			SysUser sysUser = (SysUser) object;
			List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
			sysUser.setUserRoles(userRoles);
			sysUser.setRoleNames(getRoleNames(userRoles));
		}
	}

	private String getRoleNames(List<SysUserRole> userRoles) {
		StringBuilder sb = new StringBuilder();
		for(Iterator<SysUserRole> iter = userRoles.iterator(); iter.hasNext();) {
			SysUserRole userRole = iter.next();
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
			if(sysRole == null) {
				continue ;
			}
			sb.append(sysRole.getRemark());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public Set<String> findPermissions(String userName) {	
		Set<String> perms = new HashSet<>();
		List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
		for(SysMenu sysMenu:sysMenus) {
			if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
				perms.add(sysMenu.getPerms());
			}
		}
		return perms;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		return sysUserRoleMapper.findUserRoles(userId);
	}

	@Override
	public int unlockUser(Long id) throws Exception {

		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		log.info("--用户状态--sysUser:{}", sysUser);
		if(null != sysUser && UserStatusEnum.LOCKING.getCode().equals(sysUser.getStatus())){
			SysUser user = new SysUser();
			user.setId(id);
			user.setLoginErrorTimes(0);
			user.setStatus(UserStatusEnum.NORMAL.getCode());
			return sysUserMapper.updateByPrimaryKeySelective(user);
		}else{
			return -1;
		}
	}
	@Override
	public int loginTimeReset(Long userId) {
		SysUser user = new SysUser();
		user.setId(userId);
		user.setLoginErrorTimes(0);
		int update = sysUserMapper.updateByPrimaryKeySelective(user);
		log.info("--用户状态重置--update:{}", update > 0);
        return update;
	}

	@Override
	public int loginErr(Long userId) {
		log.info("--用户登录异常--userId:{}", userId);
		return sysUserMapper.updateErrorTimes(userId, Integer.parseInt(maxTime));
	}

}
