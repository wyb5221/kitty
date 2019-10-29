package com.louis.kitty.admin.controller;

import com.louis.kitty.admin.dto.resp.ApiRolesDTO;
import com.louis.kitty.admin.sevice.ApiRolesQueryService;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.service.ApiRolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiRolesController implements ApiRolesService {

    @Autowired
    ApiRolesQueryService apiRolesQueryService;

    @Override
    public HttpResult queryApiRoles(String apiUrl) {
        log.info("--查询api角色权限入参apiUrl:{}--", apiUrl);
        ApiRolesDTO apiRolesDTO = apiRolesQueryService.queryApiRoles(apiUrl);

        return HttpResult.ok(apiRolesDTO);
    }

}
