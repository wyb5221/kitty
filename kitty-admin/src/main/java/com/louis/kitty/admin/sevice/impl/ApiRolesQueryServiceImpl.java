package com.louis.kitty.admin.sevice.impl;

import com.louis.kitty.admin.dao.DataOperateMyMapper;
import com.louis.kitty.admin.dto.resp.ApiRolesDTO;
import com.louis.kitty.admin.model.SysApi;
import com.louis.kitty.admin.sevice.ApiRolesQueryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiRolesQueryServiceImpl implements ApiRolesQueryService {

    @Autowired
    DataOperateMyMapper dataOperateMyMapper;

    @Override
    public ApiRolesDTO queryApiRoles(String apiUrl) {
        ApiRolesDTO apiRolesDTO = new ApiRolesDTO();
        String[] pathList = StringUtils.split(apiUrl,"/");
        String gateCode = pathList[1];//网关编号，比如/gate/01,则编号为01
        //接口地址url
        String url = "/"+StringUtils.join(pathList,"/",2,pathList.length);

        SysApi sysApi = dataOperateMyMapper.querySysApiId(gateCode, url);
        apiRolesDTO.setApiType(sysApi.getApiType());
        apiRolesDTO.setAuthType(sysApi.getAuthType());

        List<String> roleList = dataOperateMyMapper.querySysRoles(sysApi.getId());
        apiRolesDTO.setRoles(roleList);

        return apiRolesDTO;
    }

    public static void main(String[] args) {

        String a = "/gate/01/api/v1/userInfo";
        String[] pathList = StringUtils.split(a,"/");
        System.out.println(pathList[1]);
        String apiUrl = "/"+StringUtils.join(pathList,"/",2,pathList.length);
        System.out.println(apiUrl);
    }
}
