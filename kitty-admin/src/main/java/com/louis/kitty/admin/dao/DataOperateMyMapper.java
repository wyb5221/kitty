package com.louis.kitty.admin.dao;

import com.louis.kitty.admin.dto.resp.SysInfoMenuDTO;
import com.louis.kitty.admin.model.SysApi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataOperateMyMapper {

    //根据网关编号和api地址查询api表信息
    SysApi querySysApiId(@Param(value="gateCode") String gateCode, @Param(value="url") String url);

    //根据api接口id查询接口的角色权限
    List<String> querySysRoles(Long id);

    //查询系统对应的菜单
    List<SysInfoMenuDTO> querySysInfoMenu();
}
