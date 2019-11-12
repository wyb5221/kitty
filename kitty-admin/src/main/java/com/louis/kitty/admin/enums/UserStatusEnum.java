package com.louis.kitty.admin.enums;

import lombok.Getter;

/**
 *
 */
@Getter
public enum UserStatusEnum {
    /**
     * 状态  0：禁用   1：正常：2：锁定 3：注销
     * */
    PROHIBIT(0, "禁用"),
    NORMAL(1, "正常"),
    LOCKING(2, "锁定"),
    CANCELLATION(3, "注销");

    private Integer code;
    private String desc;

    UserStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举值code查询状态描述
     * @param code
     * @return
     */
    public static String getDescByCode(Integer code){
        for(UserStatusEnum delEnum : UserStatusEnum.values()){
            if(delEnum.getCode().equals(code)){
                return delEnum.getDesc();
            }
        }
        return null;
    }
}
