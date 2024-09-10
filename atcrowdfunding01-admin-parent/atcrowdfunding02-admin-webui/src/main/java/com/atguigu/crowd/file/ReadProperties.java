package com.atguigu.crowd.file;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author shuyun
 * @date 2024-09-10 22:17:58
 */
public class ReadProperties {

    public static Properties readPropertiesByInputStream(){

        Properties pros = new Properties();
        try {
            FileInputStream fis = new FileInputStream("D:\\workspace\\CROWD\\atcrowdfunding01-admin-parent\\atcrowdfunding02-admin-webui\\src\\main\\resources\\properties\\code.properties");
            pros.load(fis);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pros;
    }

    public static void main(String[] args) {
        //遍历配置文件
        Properties properties = readPropertiesByInputStream();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object,Object> map : entries){
            System.out.println(map.getKey()+":"+map.getValue());
        }

    }

}
