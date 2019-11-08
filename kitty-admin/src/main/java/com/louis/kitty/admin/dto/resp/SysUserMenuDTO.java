package com.louis.kitty.admin.dto.resp;

import com.louis.kitty.admin.model.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysUserMenuDTO extends SysMenu {

    /** 系统表主键 */
    private Long infokey;
    /** 系统名称 */
    private String infoName;
    /** 系统名称 */
    private String code;

    @Override
    public String toString() {
        return "SysUserMenuDTO{" +
                "infokey=" + infokey +
                ", infoName='" + infoName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
