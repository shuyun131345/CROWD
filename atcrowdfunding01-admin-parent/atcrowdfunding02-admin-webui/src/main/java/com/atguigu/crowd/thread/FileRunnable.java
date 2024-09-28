package com.atguigu.crowd.thread;

import java.io.*;
import java.util.Base64;

public class FileRunnable implements Runnable {

    private static  int fileNUmber = 10000;
    String odlFilePath = "D:\\workspace\\CROWD\\atcrowdfunding01-admin-parent\\atcrowdfunding02-admin-webui\\src\\main\\resources\\oldwave\\14026.wav";
    String  newFilePath = "D:\\workspace\\CROWD\\atcrowdfunding01-admin-parent\\atcrowdfunding02-admin-webui\\src\\main\\resources\\newwave\\";

    @Override
    public void run() {


        //假设每个线程要执行300次任务
        for (int i=0; i<1; i++){
            //获取文件路径
            String filePath = getFilePath();
            //获取音频的base64加密的字符串
            String oldWave = getBase64Wave(odlFilePath);
            System.out.println(filePath+"===长度="+oldWave.length());
//            System.out.println("==========================="+oldWave.getBytes().length+"============"+oldWave);
            //写出到新文件
            saveWaveFile(filePath,oldWave);

        }
    }

    //获取新的文件路径
    private  String getFilePath(){
        String path;
        synchronized (fileNUmber+""){
            if (fileNUmber <= 10000+2000) {
                fileNUmber++;
            }else {
                fileNUmber = 10000;
            }
            path = new String(newFilePath+fileNUmber+".wav");
//            System.out.println(Thread.currentThread().getName()+"线程：文件数为："+fileNUmber);
        }


        return path;
    }

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
            System.out.println("============解密："+waveBytes.length);
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
