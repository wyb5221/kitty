package com.louis.kitty.admin.dto.resp;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class ApiRolesDTO implements Serializable {

    private static final long serialVersionUID = 2728653950198086251L;

    private String apiType;//接口类型R读W写

    private String authType;//权限类型：N 无需控制；L 登录控制； R 权限控制

    private List<String> roles;//角色列表

    @Override
    public String toString() {
        return "ApiRolesDTO{" +
                "apiType='" + apiType + '\'' +
                ", authType='" + authType + '\'' +
                ", roles=" + roles +
                '}';
    }
}
