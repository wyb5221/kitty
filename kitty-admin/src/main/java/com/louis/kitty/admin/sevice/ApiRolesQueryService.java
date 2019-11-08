package com.louis.kitty.admin.sevice;

import com.louis.kitty.admin.dto.resp.ApiRolesDTO;

public interface ApiRolesQueryService {

    /**
     * 查询api的用户角色
     * @param apiUrl
     * @return
     */
    ApiRolesDTO queryApiRoles(String apiUrl);

}
