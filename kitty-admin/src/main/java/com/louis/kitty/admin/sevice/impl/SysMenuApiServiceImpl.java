package com.louis.kitty.admin.sevice.impl;

import com.louis.kitty.admin.dao.SysMenuApiMapper;
import com.louis.kitty.admin.dto.BindRequest;
import com.louis.kitty.admin.model.SysMenuApi;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.sevice.SysMenuApiService;
import com.louis.kitty.admin.util.WebUtils;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 角色菜单 (SysMenuApiServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-29 10:16:45
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class SysMenuApiServiceImpl implements SysMenuApiService {

	@Autowired
	private SysMenuApiMapper sysMenuApiMapper;

	@Autowired
	private WebUtils webUtils;

	@Override
	public int save(SysMenuApi record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysMenuApiMapper.add(record);
		}
		return sysMenuApiMapper.update(record);
	}

	@Override
	public int delete(SysMenuApi record) {
		return sysMenuApiMapper.delete(record.getId());
	}

	@Override
	public int delete(List<SysMenuApi> records) {
		for(SysMenuApi record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysMenuApi findById(Long id) {
		return sysMenuApiMapper.findById(id);
	}

	@Override
	public int update(SysMenuApi record) {
		return 0;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysMenuApiMapper);
	}

	@Override
	public int bindApi(BindRequest request) {

		//param parse
		SysUser user = webUtils.getUserSession();
		List<SysMenuApi> bindList = request.getBindList();
		bindList.forEach(sysMenuApi -> {
			sysMenuApi.setCreateBy(user.getUserName());
			sysMenuApi.setCreateTime(new Date());
		});

		//insert
		return sysMenuApiMapper.insertBatch(bindList);
	}

	@Override
	public int unbindApi(BindRequest request) {
		return sysMenuApiMapper.deleteBatch(request.getBindList());
	}
}
