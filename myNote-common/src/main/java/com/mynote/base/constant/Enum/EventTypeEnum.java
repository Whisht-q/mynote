package com.mynote.base.constant.Enum;

import lombok.Getter;

/**
 * @author zhishubin
 * @date 2023/12/19 12:03
 * @description
 */
@Getter
public enum EventTypeEnum {
    // 登录
    LOGIN("100001","登录")
    ;

    private final String code;

    private final String type;

    EventTypeEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }
}
