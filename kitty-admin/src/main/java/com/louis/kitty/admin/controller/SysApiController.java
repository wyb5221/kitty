package com.louis.kitty.admin.controller;

import com.louis.kitty.admin.model.SysApi;
import com.louis.kitty.admin.sevice.SysApiService;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.http.HttpStatus;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 系统api配置表 (SysApiController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:25
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Slf4j
@RestController
@RequestMapping("sysApi")
public class SysApiController {

	@Autowired
	private SysApiService sysApiService;

	/**
	 * 保存系统api配置表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysApi record) {
		return HttpResult.ok(sysApiService.save(record));
	}

    /**
     * 删除系统api配置表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysApi> records) {
		return HttpResult.ok(sysApiService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		log.info("--findPage--");
		return HttpResult.ok(sysApiService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		log.info("--findById开始--");
		return HttpResult.ok(sysApiService.findById(id));
	}
	/**
	 * 修改系系统api配置
	 * @param record
	 * @return
	 */
	@PostMapping(value="/update")
	public HttpResult update(@RequestBody SysApi record) {
		log.info("--update--修改系统表数据入参record：{}", record);
		return sysApiService.update(record) > 0 ? HttpResult.ok() : HttpResult.error();
	}

	/**
	 * 根据微服务id查询已绑定菜单的一份列表，和未绑定菜单一份列表
	 */
	@GetMapping(value="/findByKey")
	public HttpResult findByKey(@RequestParam Long serviceKey, @RequestParam Long menuKey) {
		if (serviceKey == null || menuKey == null){
			return HttpResult.error(HttpStatus.SC_BAD_REQUEST, "参数不能为空");
		}
		return HttpResult.ok(sysApiService.findByKey(serviceKey, menuKey));
	}
}
