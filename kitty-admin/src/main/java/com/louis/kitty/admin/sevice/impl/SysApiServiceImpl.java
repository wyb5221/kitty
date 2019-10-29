package com.louis.kitty.admin.sevice.impl;

import com.louis.kitty.admin.dao.SysApiMapper;
import com.louis.kitty.admin.model.SysApi;
import com.louis.kitty.admin.sevice.SysApiService;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ---------------------------
 * 系统api配置表 (SysApiServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:25
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class SysApiServiceImpl implements SysApiService {

	@Autowired
	private SysApiMapper sysApiMapper;

	@Override
	public int save(SysApi record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysApiMapper.add(record);
		}
		return sysApiMapper.update(record);
	}

	@Override
	public int delete(SysApi record) {
		return sysApiMapper.delete(record.getId());
	}

	@Override
	public int delete(List<SysApi> records) {
		for(SysApi record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysApi findById(Long id) {
		return sysApiMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysApiMapper);
	}


}
