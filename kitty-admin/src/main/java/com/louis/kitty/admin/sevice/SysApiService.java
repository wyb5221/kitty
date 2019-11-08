package com.louis.kitty.admin.sevice;

import com.louis.kitty.admin.model.SysApi;
import com.louis.kitty.core.service.CurdService;

import java.util.Map;

/**
 * ---------------------------
 * 系统api配置表 (SysApiService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:25
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysApiService extends CurdService<SysApi> {

    Map findByKey(Long serviceKey, Long menuKey);
}
