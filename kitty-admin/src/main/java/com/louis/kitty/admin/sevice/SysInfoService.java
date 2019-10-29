package com.louis.kitty.admin.sevice;

import com.louis.kitty.admin.dto.SysInfoQueryRequest;
import com.louis.kitty.admin.model.SysInfo;
import com.louis.kitty.core.page.PageResult;
import com.louis.kitty.core.service.CurdService;

import java.util.List;

/**
 * ---------------------------
 * 系统表 (SysInfoService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-10-28 10:36:24
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysInfoService extends CurdService<SysInfo> {

    /**
     * 删除系统表数据
     * @param delFlag
     * @param sysInfos
     * @return
     */
    int updateDel(List<SysInfo> sysInfos);

    int updateInfo(SysInfo sysInfo);

    PageResult findInfoByPage(SysInfoQueryRequest request);
}
