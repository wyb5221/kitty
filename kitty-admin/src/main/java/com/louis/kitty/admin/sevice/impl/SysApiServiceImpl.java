package com.louis.kitty.admin.sevice.impl;

import com.louis.kitty.admin.dao.SysApiMapper;
import com.louis.kitty.admin.dao.SysMenuApiMapper;
import com.louis.kitty.admin.model.SysApi;
import com.louis.kitty.admin.model.SysMenuApi;
import com.louis.kitty.admin.sevice.SysApiService;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	private static final String BIND_LIST = "bindList";
	private static final String BINDING_LIST = "bindingList";

	@Autowired
	private SysMenuApiMapper sysMenuApiMapper;
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
		PageResult pageResult = null;
		String name = getColumnFilterValue(pageRequest, "name");
		if(name != null) {
			pageResult = MybatisPageHelper.findPage(pageRequest, sysApiMapper, "findPageByName", name);
		} else {
			pageResult = MybatisPageHelper.findPage(pageRequest, sysApiMapper);
		}
		return pageResult;
	}
	public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		if(columnFilter != null) {
			value = columnFilter.getValue();
		}
		return value;
	}


	@Override
	public int update(SysApi sysApi) {
		if (sysApi == null || sysApi.getId() == null) {
			return 0;
		}
		return sysApiMapper.update(sysApi);
	}

	@Override
	public Map findByKey(Long serviceKey, Long menuKey, String name) {
		Map<String, List<SysApi>> result = new HashMap<>(4);

		//查询api和菜单api
		List<SysApi> sysApis = sysApiMapper.queryByKey(serviceKey, name);
		List<SysMenuApi> sysMenuApis = sysMenuApiMapper.queryByMenuKey(menuKey);

		//区分已经绑定api和未绑定api
		List<SysApi> bindList = new ArrayList<>();
		List<SysApi> bingingList;
		if (CollectionUtils.isEmpty(sysApis) || CollectionUtils.isEmpty(sysMenuApis)) {
			bingingList = sysApis;
		}else {
			List<Long> apiKeys = sysMenuApis.stream().map(SysMenuApi::getApiKey).collect(Collectors.toList());
			bindList = sysApis.stream().filter(sysApi -> apiKeys.contains(sysApi.getId())).collect(Collectors.toList());
			bingingList = sysApis.stream().filter(sysApi -> !apiKeys.contains(sysApi.getId())).collect(Collectors.toList());
		}
		result.put(BIND_LIST, bindList);
		result.put(BINDING_LIST, bingingList);
		return result;
	}
}
