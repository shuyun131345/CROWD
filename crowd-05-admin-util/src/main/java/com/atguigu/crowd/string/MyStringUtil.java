package com.atguigu.crowd.string;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author shuyun
 * @date 2024-09-11 08:48:08
 */
public class MyStringUtil {


    /**
     * 生成指定长度的随机的字符和数字组成的字符串
     * @param length
     * @return
     */
    public static String randSerial(int length){

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i =0; i < length; i++){

            //数字还是字符串:0-数字，1-字符串
            int charType = random.nextInt(2);
            if (0 == charType){
                sb.append(randNumber(1));
            }else {
                sb.append(randChar(1));
            }
        }
        return sb.toString();
    }




    /**
     * 生成指定长度的随机的字符串数字
     * @param length
     * @return
     */
    public static String randNumber(int length){

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++){

            //生成一个0-9之间的随机数
            sb.append(secureRandom.nextInt(10));
        }
        return sb.toString();
    }


    /**
     * 生成指定长度的随机的字符串(随机大小写)
     * @param length
     * @return
     */
    public static String randChar(int length){

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++){
            //大写字母还是小写字母
            int codeType = random.nextInt(2) % 2 == 0 ? 65 : 97;
            //用ascii码
            char c = (char) (codeType + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * 生成指定长度范围的随机的字符串
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static String randRangeChar(int beginIndex,int endIndex){

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int number = random.nextInt(endIndex - beginIndex + 1) + beginIndex;
        for (int i = 0; i < number; i++){
            //大写字母还是小写字母
            int codeType = random.nextInt(2) % 2 == 0 ? 65 : 97;
            //用ascii码
            char c = (char) (codeType + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }


}
