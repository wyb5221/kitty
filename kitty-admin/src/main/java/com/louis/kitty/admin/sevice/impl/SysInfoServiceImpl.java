package com.louis.kitty.admin.sevice.impl;

import com.alibaba.fastjson.JSON;
import com.louis.kitty.admin.dao.SysInfoMapper;
import com.louis.kitty.admin.dto.SysInfoQueryRequest;
import com.louis.kitty.admin.model.SysInfo;
import com.louis.kitty.admin.sevice.SysInfoService;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ---------------------------
 * 系统表 (SysInfoServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:24
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Slf4j
@Service
public class SysInfoServiceImpl implements SysInfoService {

	@Autowired
	private SysInfoMapper sysInfoMapper;

	@Override
	public int save(SysInfo record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysInfoMapper.add(record);
		}
		return sysInfoMapper.update(record);
	}

	@Override
	public int delete(SysInfo record) {
		return sysInfoMapper.delete(record.getId());
	}

	@Override
	public int delete(List<SysInfo> records) {
		for(SysInfo record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysInfo findById(Long id) {
		return sysInfoMapper.findById(id);
	}

	@Override
	public int update(SysInfo record) {
		return 0;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysInfoMapper);
	}

	@Override
	public int updateDel(List<SysInfo> sysInfos) {
		log.info("--updateDel修改删除状态sql入参--sysInfos:{}", sysInfos.toArray());
		return sysInfoMapper.updateDel(sysInfos);
	}

	@Override
	public int updateInfo(SysInfo sysInfo) {
		log.info("--updateInfo修改系统表数据sql入参--sysInfo:{}", sysInfo);
		return sysInfoMapper.update(sysInfo);
	}

	@Override
	public PageResult findInfoByPage(SysInfoQueryRequest request) {
		log.info("--findInfoByPage查询系统表数据sql入参--sysInfo:{}", JSON.toJSONString(request));

		return null;
	}


}
