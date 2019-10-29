package com.louis.kitty.core.service;

import com.louis.kitty.core.http.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface ApiRolesService {

    @GetMapping("/apiRoles")
    HttpResult queryApiRoles(String apiUrl);
}
