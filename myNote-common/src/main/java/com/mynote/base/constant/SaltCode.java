package com.mynote.base.constant;

/**
 * @author zhishubin
 * @date 2023/12/14 16:52
 * @description
 */
public interface SaltCode {
    /**
     * 静态盐
     */
    String SALTMSG = "7f62ebb0ebf443058549b3930dfc0f95";
    /**
     * 字符常量
     */
    String CHARACTERS = "abcdefghijklmnopqrstuvwxyz,./<>?!@#$%^&*()_+-=[{}]:;'`~|ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 随机盐长度
     */
    int SALTLENGTH = 24;
}
