package com.atguigu.crowd.common;

import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.atguigu.crowd.constant.CrowdConstant.MD5;
import static com.atguigu.crowd.constant.ExceptionConstant.ATTR_INVALIDATE;

/**
 * @author shuyun
 * @date 2024-08-18 22:06:53
 */
public class EncodeByMD5 {

    /**
     * 对 明文字符串 进行md5加密
     * @param src
     * @return
     */
    public static String encode(String src) {
        if (StringUtils.isEmpty(src)) {
            throw new RuntimeException(ATTR_INVALIDATE);
        }

        try {
            // 获取 MessageDigest 对象
            MessageDigest digest = MessageDigest.getInstance(MD5);
            // 对字符串进行md5加密
            byte[] dest = digest.digest(src.getBytes());
            // 创建 BigInteger 对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, dest);

            //按照 16 进制将 bigInteger 的值转换为字符串
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }
}
