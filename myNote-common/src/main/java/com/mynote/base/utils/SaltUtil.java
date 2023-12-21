package com.mynote.base.utils;

import java.security.SecureRandom;

import static com.mynote.base.constant.SaltCode.CHARACTERS;

/**
 * @author zhishubin
 * @date 2023/12/14 16:44
 * @description
 */
public class SaltUtil {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成随机盐
     *
     * @param length 随机盐长度
     * @return 随机盐
     */
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
