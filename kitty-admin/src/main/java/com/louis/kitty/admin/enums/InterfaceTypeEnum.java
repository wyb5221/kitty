package com.louis.kitty.admin.enums;

import lombok.Getter;

/**
 * 查询菜单枚举
 */
@Getter
public enum InterfaceTypeEnum {

    OTHER("other", "其他"),
    ROLE("role", "角色菜单查询");

    private String code;
    private String desc;

    InterfaceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举值code查询状态描述
     * @param code
     * @return
     */
    public static String getDescByCode(String code){
        for(InterfaceTypeEnum ItEnum : InterfaceTypeEnum.values()){
            if(ItEnum.getCode().equals(code)){
                return ItEnum.getDesc();
            }
        }
        return null;
    }
}
