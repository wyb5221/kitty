package com.louis.kitty.admin.enums;

import lombok.Getter;

/**
 * 菜单类型枚举
 */
@Getter
public enum MenuTypeEnum {
    //类型   0：目录   1：菜单   2：按钮  3：接口
    CATALOG(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮"),
    INTERFACE(3, "接口");

    private Integer code;
    private String desc;

    MenuTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举值code查询状态描述
     * @param code
     * @return
     */
    public static String getDescByCode(Integer code){
        for(MenuTypeEnum mtEnum : MenuTypeEnum.values()){
            if(mtEnum.getCode().equals(code)){
                return mtEnum.getDesc();
            }
        }
        return null;
    }

}
