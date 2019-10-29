package com.louis.kitty.admin.dao;

import com.louis.kitty.admin.model.SysApi;

import java.util.List;

/**
 * ---------------------------
 * 系统api配置表 (SysApiMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:25
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysApiMapper {

	/**
	 * 添加系统api配置表
	 * @param record
	 * @return
	 */
    int add(SysApi record);

    /**
     * 删除系统api配置表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改系统api配置表
     * @param record
     * @return
     */
    int update(SysApi record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    SysApi findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<SysApi> findPage();
    
}