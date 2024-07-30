package com.atguigu.crowd.wave;

import java.io.*;
import java.util.Base64;

public class TransWave {

    private String odlFilePath = "src/main/resources/oldwave/14026.wav";

    private String newFilePath = "src/main/resources/newwave/20240303.wav";


    /**
     * 将加密的base64音频解密后保存到新的路径
     * @param newFilePath
     * @param base64
     */
    public void saveWaveFile(String newFilePath,String base64){
        BufferedOutputStream bos = null;
        byte[] waveBytes = null;

        try {
            //造文件
            File desFile = new File(newFilePath);
            //造流
            FileOutputStream fos = new FileOutputStream(desFile);
            //造缓冲流
            bos = new BufferedOutputStream(fos);

            //解密
            waveBytes = Base64.getDecoder().decode(base64);

            //写出
            bos.write(waveBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    /**
     * 将音频文件转换成byte[]数组加密成base的字符串
     * @param srcFilePath 音频路径
     * @return 加密后的字符串
     */
    public String getBase64Wave(String srcFilePath){

        BufferedInputStream bis = null;
        byte[] waveBytes = null;


        try {
            //造文件
            File srcFile = new File(srcFilePath);
            //造流
            FileInputStream fis = new FileInputStream(srcFile);
            //造缓冲流
            bis = new BufferedInputStream(fis);
            //读入
            waveBytes = new byte[(int)srcFile.length()+1];
            int length;

            do{
                length = bis.read(waveBytes);
            }while (length != -1);

        } catch ( IOException e){
            e.printStackTrace();
        }finally {

            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return Base64.getEncoder().encodeToString(waveBytes);
    }



}
