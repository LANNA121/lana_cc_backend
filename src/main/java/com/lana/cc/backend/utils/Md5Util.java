package com.lana.cc.backend.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/19 23:22
 */
public class Md5Util {
    public static String encodeByMd5(String value,String slat,int uid) {
        String passString = String.format("L%sA%sN%sA",value,slat,uid);
        return DigestUtils.md5DigestAsHex(passString.getBytes());
    }
}
