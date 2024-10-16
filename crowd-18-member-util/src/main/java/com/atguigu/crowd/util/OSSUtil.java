package com.atguigu.crowd.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.atguigu.crowd.entity.ResultEntity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * 阿里云 OSS存储工具类
 */
public class OSSUtil {


    /**
     * 将文件上传到OSS服务器
     *
     * @param endPoint        OSS参数
     * @param bucketName      OSS参数
     * @param accessKeyId     OSS参数
     * @param accessKeySecret OSS参数
     * @param bucketDomain    OSS参数
     * @param inputStream     输入流
     * @param originalName    原始文件名
     * @return
     */
    public static ResultEntity<String> uploadFileToOss(
            String endPoint,
            String bucketName,
            String accessKeyId,
            String accessKeySecret,
            String bucketDomain,
            InputStream inputStream,
            String originalName) {


        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        //生成上传文件的目录：按上传日期 （年月日）作为文件夹存储上传的文件
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        //生成上传文件在OSS服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg
        //使用UUID生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");

        //从原始文件名中获取文件拓展名，即文件名的后缀
        String extendsionName = originalName.substring(originalName.lastIndexOf("."));

        //使用目录、文件主体名称、文件拓展名称拼接得到对象名称，作为该文件存储在OSS的全路径
        String objectName = folderName + "/" + fileMainName + extendsionName;

        try {
            //调用OSS客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

            //从响应结果中获取具体的响应消息
            ResponseMessage response = putObjectResult.getResponse();

            //根据响应状态码确定是否上传成功
            if (Objects.isNull(response)) {
                //response 为Null 是上传成功，返回文件的访问路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;
                return ResultEntity.successWithData(ossFileAccessPath);
            } else {
                //上传失败，获取响应状态码
                int statusCode = response.getStatusCode();
                //获取错误消息
                String errorMessage = response.getErrorResponseAsString();
                //返回失败消息
                return ResultEntity.failed("响应状态码：" + statusCode + "错误消息：" + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }finally {
            //关闭OSSClient
            if (ossClient != null){
                ossClient.shutdown();
            }
        }
    }

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
