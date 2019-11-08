package com.louis.kitty.admin.controller;

import com.louis.kitty.admin.dto.BindRequest;
import com.louis.kitty.admin.model.SysMenuApi;
import com.louis.kitty.admin.sevice.SysMenuApiService;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 角色菜单 (SysMenuApiController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-29 10:16:45
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("sysMenuApi")
public class SysMenuApiController {

	@Autowired
	private SysMenuApiService sysMenuApiService;

	/**
	 * 保存角色菜单
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysMenuApi record) {
		return HttpResult.ok(sysMenuApiService.save(record));
	}

    /**
     * 删除角色菜单
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysMenuApi> records) {
		return HttpResult.ok(sysMenuApiService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysMenuApiService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(sysMenuApiService.findById(id));
	}

	@PostMapping(value="/bindApi")
	public HttpResult bindApi(@RequestBody BindRequest request) {
		return sysMenuApiService.bindApi(request) > 0 ? HttpResult.ok() : HttpResult.error();
	}
	@PostMapping(value="/unbindApi")
	public HttpResult unbindApi(@RequestBody BindRequest request) {
		return sysMenuApiService.unbindApi(request) > 0 ? HttpResult.ok() : HttpResult.error();
	}
}
