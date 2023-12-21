package com.mynote.base.constant.Enum;

import lombok.Getter;

/**
 * @author zhishubin
 * @date 2023/12/13 15:19
 * @description 统一返回结果状态信息类
 */

@Getter
public enum ResultCodeEnum {

    //成功
    SUCCESS(200, "成功"),
    //失败
    FAIL(500, "失败"),
    // 用户名重复
    NAME_REPEAT(501, "用户名已存在"),
    // 认证失败
    AUTHENTICATION_FAILED(502, "用户名或密码错误"),
    // 验证码生成异常
    CAPTCHA_GENERATE_ERROR(503, "验证码生成失败"),
    // redis过期时间设置异常
    EXPIRE_TIME_ERROR(504, "redis过期时间设置异常"),
    // 验证码错误
    CAPTCHA_ERROR(505, "验证码错误"),
    //操作事件id重复
    EVENT_ID_REPEAT(506, "操作事件id已存在"),
    //操作事件类型已存在
    EVENT_TYPE_REPEAT(507, "操作事件类型已存在"),
    //操作事件类型不存在
    EVENT_TYPE_NONE(508, "操作事件类型不存在"),
    //用户不存在
    USER_NOT_EXIST(509, "用户不存在"),
    //角色不存在
    ROLE_NOT_EXIST(510, "角色不存在"),
    //父级分类不存在
    PARENT_CATEGORY_NONE(511,"父级分类不存在"),
    //分类已存在
    CATEGORY_IS_EXIST(512,"分类已存在"),
    //分类不存在
    CATEGORY_IS_NONE(513,"分类不存在"),
    //标题已存在
    TITLE_IS_EXIST(514,"标题已存在"),
    //笔记关联分类已存在
    NOTE_CATEGORY_IS_EXIST(515,"笔记关联分类已存在"),
    //笔记已存在
    NOTE_IS_EXIST(516,"笔记已存在"),
    //笔记不存在
    NOTE_IS_NONE(517,"笔记不存在")
    ;

    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
