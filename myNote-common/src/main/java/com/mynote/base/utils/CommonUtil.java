package com.mynote.base.utils;

/**
 * @author zhishubin
 * @date 2023/12/18 14:14
 * @description
 */
public class CommonUtil {

    public static boolean isEmpty(Object object) {
        return object == null || "".equals(object);
    }

    public static boolean isExist(int res) {
        return res != 0;
    }

    public static boolean strToBool(String str) {
        if ("-1".equals(str)) {
            return false;
        }
        return !"0".equals(str);
    }

    public static String getStr(String str) {
        return getStr(str, "");
    }

    public static String getStr(String str, String defVal) {
        if (isEmpty(str)) {
            return defVal;
        }
        return str.trim();
    }

    public static int getInt(String s, int defval) {
        if (isEmpty(s)) {
            return defval;
        }
        try {
            return (Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return defval;
        }
    }

    public static int getInt(String s) {
        if (isEmpty(s)) {
            return 0;
        }
        try {
            return (Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
