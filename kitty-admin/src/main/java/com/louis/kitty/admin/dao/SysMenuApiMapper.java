package com.louis.kitty.admin.dao;

import com.louis.kitty.admin.model.SysMenuApi;

import java.util.List;

/**
 * ---------------------------
 * 角色菜单 (SysMenuApiMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-29 10:16:45
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysMenuApiMapper {

	/**
	 * 添加角色菜单
	 * @param record
	 * @return
	 */
    int add(SysMenuApi record);

    /**
     * 删除角色菜单
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改角色菜单
     * @param record
     * @return
     */
    int update(SysMenuApi record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    SysMenuApi findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<SysMenuApi> findPage();
    
}