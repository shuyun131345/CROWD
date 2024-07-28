package com.atguigu.crowd.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class XmlBuilder {


    /**
     * 根据xml文件转换成JavaBean
     * @param type
     * @param filePath
     * @return
     * @param <T>
     */
    public static <T> T xmlFileToJavaBean(Class<?> type,String filePath){
        FileReader fis = null;
        StringBuilder sb = null;
        try {
            File city = new File(filePath);
            sb = new StringBuilder();
            char[] buf = new char[50];
            int len;
            fis = new FileReader(city);
            while ((len=fis.read(buf))!=-1){
                sb.append(buf,0,len);
            }
            System.out.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return xmlToJavaBean(type,sb.toString());
    }

    /**
     * XML(String)转换成JavaBean
     * @param type
     * @param xml
     * @return
     * @param <T>
     */
    public static <T> T xmlToJavaBean(Class<?> type,String xml){

        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            T unmarshal = (T)unmarshaller.unmarshal(reader);
            reader.close();
            return unmarshal;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
