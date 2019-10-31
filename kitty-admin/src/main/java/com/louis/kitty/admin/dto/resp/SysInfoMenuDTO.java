package com.louis.kitty.admin.dto.resp;

import com.louis.kitty.admin.model.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysInfoMenuDTO {

    /** 系统表主键 */
    private Long infokey;
    /** 系统名称 */
    private String name;
    /** 系统名称 */
    private String code;
    /** 系统菜单 */
    private List<SysMenu> menuList;

    @Override
    public String toString() {
        return "SysInfoMenuDTO{" +
                "infokey=" + infokey +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", menuList=" + menuList +
                '}';
    }
}
