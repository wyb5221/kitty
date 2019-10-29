package com.louis.kitty.admin.enums;

import lombok.Getter;

/**
 *
 */
@Getter
public enum DelFlagEnum {

    UNDELETED(0, "未删除"),
    DELETED(1, "已删除");

    private Integer code;
    private String desc;

    DelFlagEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举值code查询状态描述
     * @param code
     * @return
     */
    public static String getDescByCode(Integer code){
        for(DelFlagEnum delEnum : DelFlagEnum.values()){
            if(delEnum.getCode().equals(code)){
                return delEnum.getDesc();
            }
        }
        return null;
    }
}
