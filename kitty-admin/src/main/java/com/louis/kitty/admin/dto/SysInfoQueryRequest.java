package com.louis.kitty.admin.dto;

import com.louis.kitty.core.page.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysInfoQueryRequest extends PageRequest {

    /** 系统编码，唯一 */
    private String code;
    /** 系统名称 */
    private String name;

    @Override
    public String toString() {
        return "SysInfoQueryRequest{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
