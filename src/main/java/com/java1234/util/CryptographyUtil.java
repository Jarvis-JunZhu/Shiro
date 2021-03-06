package com.java1234.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * base64加密解密
 * Created by Jarvis on 2017/5/7.
 */
public class CryptographyUtil {

    /**
     * base64加密
     *
     * @param str
     * @return
     */
    public static String encBase64(String str) {
        return Base64.encodeToString(str.getBytes());
    }

    /**
     * base64解密
     *
     * @param str
     * @return
     */
    public static String decBase64(String str) {
        return Base64.decodeToString(str);
    }

    /**
     * MD5加密
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {
        String password = "123456";
        String temp     = encBase64(password);
        System.out.println("Base64加密：" + temp);
        System.out.println("Base64解密:" + decBase64(temp));
        System.out.println("Md5加密:"+md5(password,"java1234"));
    }


}
