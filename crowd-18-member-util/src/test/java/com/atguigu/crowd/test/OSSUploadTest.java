package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.util.OSSUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OSSUploadTest {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("D:\\picture\\3333.jpg");
        ResultEntity<String> stringResultEntity = OSSUtil.uploadFileToOss("http://oss-cn-guangzhou.aliyuncs.com",
                "shuyun-atguigu",
                "LTAI5tQ1J43vbuyuQUsFsXoM",
                "pPTj0VnFfyVDb2towQgQmTNZJW9LnS",
                "http://shuyun-atguigu.oss-cn-guangzhou.aliyuncs.com",
                inputStream,
                "3333.jpg");
        System.out.println(stringResultEntity);

    }
}
