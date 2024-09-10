package com.atguigu.crowd.file;

import java.io.*;
import java.util.Properties;

/**
 * @author shuyun
 * @date 2024-09-10 21:05:04
 */
public class FileReplace {

    public static void main(String[] args) {

        //源文件夹路径
        String srcFilePath = "D:\\workspace\\CROWD\\atcrowdfunding01-admin-parent\\atcrowdfunding02-admin-webui\\src\\main\\resources\\oldfile\\";
        //新文件夹路径
        String desFilePath = "D:\\workspace\\CROWD\\atcrowdfunding01-admin-parent\\atcrowdfunding02-admin-webui\\src\\main\\resources\\newfile\\";


        FileReader fr = null;
        FileWriter fw = null;

        File srcFile = new File(srcFilePath);

        Properties properties = ReadProperties.readPropertiesByInputStream();
        File[] listFiles = srcFile.listFiles();

        for (File file : listFiles){
            char[] cbuf = new char[(int)file.length()];
            int length = 0;

            try {
                fr = new FileReader(file);
                while ((length = fr.read(cbuf) )!= -1){
                    fr.read(cbuf,0,length);
                }

                //源字符串
                String text = new String(cbuf);
                System.out.println(text.substring(0,text.indexOf("<ENTITY>")));
                System.out.println(text.substring(text.indexOf("</COMMON>")));

                //配置文件中的要替换的内容
                String replace =(String)properties.get(file.getName().replaceAll(".txt", ""));

                //替换后字符串
                String newTx = text.substring(0,text.indexOf("<ENTITY>"))+replace+text.substring(text.indexOf("</COMMON>"));

                File desFile = new File(desFilePath+file.getName());
                fw = new FileWriter(desFile);
                fw.write(newTx,0,newTx.length());

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (fr != null){
                        fr.close();
                    }
                    if (fw != null){
                        fw.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            }

        }




    }


}
