package com.louis.kitty.admin.controller;

import com.louis.kitty.admin.model.SysService;
import com.louis.kitty.admin.sevice.SysServiceService;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 微服务表 (SysServiceController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:24
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("sysService")
@Slf4j
public class SysServiceController {

	@Autowired
	private SysServiceService sysServiceService;

	/**
	 * 保存微服务表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysService record) {
		return HttpResult.ok(sysServiceService.save(record));
	}

    /**
     * 删除微服务表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysService> records) {
		return HttpResult.ok(sysServiceService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysServiceService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(sysServiceService.findById(id));
	}
    /**
     * 根据系统id查询
     * @param sysKey
     * @return
     */
	@GetMapping(value="/findBySysKey")
	public HttpResult findBySysKey(@RequestParam Long sysKey) {
		return HttpResult.ok(sysServiceService.findBySysKey(sysKey));
	}
	/**
	 * 修改微服务配置
	 * @param record
	 * @return
	 */
	@PostMapping(value="/update")
	public HttpResult update(@RequestBody SysService record) {
		log.info("--update--修改微服务表数据入参record：{}", record);
		return sysServiceService.update(record) > 0 ? HttpResult.ok() : HttpResult.error();
	}
}
