package com.louis.kitty.admin.dao;

import com.louis.kitty.admin.model.SysService;

import java.util.List;

/**
 * ---------------------------
 * 微服务表 (SysServiceMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:24
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysServiceMapper {

	/**
	 * 添加微服务表
	 * @param record
	 * @return
	 */
    int add(SysService record);

    /**
     * 删除微服务表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改微服务表
     * @param record
     * @return
     */
    int update(SysService record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    SysService findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<SysService> findPage();
    
}