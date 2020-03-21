package com.lana.cc.backend.utils;

/**
 * 对象校验工具
 *
 * @author LANA
 * @version 1.0
 * @date 2020/2/23 14:24
 */
public class ObjectUtil {

    public static boolean isNotEmpty(String strObj) {
        return strObj != null && !"".equals(strObj);
    }

    public static boolean isNotEmpty(Long longObj) {
        return longObj != null && 0 != longObj;
    }

    public static boolean isNotEmpty(Integer intObj) {
            return intObj != null && 0 != intObj;
    }
}
