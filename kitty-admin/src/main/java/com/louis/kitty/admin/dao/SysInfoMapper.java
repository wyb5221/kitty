package com.louis.kitty.admin.dao;

import com.louis.kitty.admin.model.SysInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 系统表 (SysInfoMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:24
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysInfoMapper {

	/**
	 * 添加系统表
	 * @param record
	 * @return
	 */
    int add(SysInfo record);

    /**
     * 删除系统表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改系统表
     * @param record
     * @return
     */
    int update(SysInfo record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    SysInfo findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<SysInfo> findPage();

    /**
     * 修改删除状态为已删除
     * @param sysInfos
     * @return
     */
    int updateDel(@Param(value = "list") List<SysInfo> sysInfos);

    /**
     * 分页查询系统数据
     * @return
     */
    List<SysInfo> findInfoByPage(SysInfo sysInfo);
}