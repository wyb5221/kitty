package com.louis.kitty.admin.controller;

import com.louis.kitty.admin.dto.SysInfoQueryRequest;
import com.louis.kitty.admin.enums.DelFlagEnum;
import com.louis.kitty.admin.model.SysInfo;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.sevice.SysInfoService;
import com.louis.kitty.admin.util.WebUtils;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 系统表 (SysInfoController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:24
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("sysInfo")
@Slf4j
public class SysInfoController {

	@Autowired
	private SysInfoService sysInfoService;

	@Autowired
	private WebUtils webutils;

	/**
	 * 保存系统表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysInfo record) {
		log.info("--save--保存系统表入参record：{}", record);

		SysUser user = webutils.getUserSession();

		record.setCreateBy(user.getUserName());
		record.setCreateTime(new Date());
		record.setLastUpdateBy(user.getUserName());
		record.setLastUpdateTime(new Date());
		record.setDelFlag(DelFlagEnum.UNDELETED.getCode());

		return HttpResult.ok(sysInfoService.save(record));
	}

    /**
     * 删除系统表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysInfo> records) {

//		return HttpResult.ok(sysInfoService.delete(records));
		return HttpResult.ok(sysInfoService.updateDel(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysInfoService.findPage(pageRequest));
	}

    /**
     * 基础分页查询
     * @param request
     * @return
     */
	@PostMapping(value="/findByPage")
	public HttpResult findInfoByPage(@RequestBody SysInfoQueryRequest request) {
		return HttpResult.ok(sysInfoService.findPage(request));
	}

    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(sysInfoService.findById(id));
	}

	/**
	 * 修改系统表数据
	 * @param record
	 * @return
	 */
	@PostMapping(value="/update")
	public HttpResult update(@RequestBody SysInfo record) {
		log.info("--update--修改系统表数据入参record：{}", record);

		SysUser user = webutils.getUserSession();

		record.setLastUpdateBy(user.getUserName());
		record.setLastUpdateTime(new Date());

		return HttpResult.ok(sysInfoService.updateInfo(record));
	}
}
