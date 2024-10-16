package com.atguigu.crowd.util;

import com.atguigu.crowd.constant.AccessPassResource;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.http.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shuyun
 * @date 2024-10-12 14:29:53
 */
public class CrowdUtil {

    /**
     * 调用第三方接口，给用户手机发送短信验证码，发送成功后将验证码返回
     *
     * @param host       接口地址
     * @param path       路径
     * @param method     http请求方式  GET  POST
     * @param appcode    APPCode
     * @param mobile     接收短信的手机号
     * @param smsSignId  短信前缀，即短信签名id
     * @param templateId 短信模板
     * @return
     */
    public static ResultEntity<String> sendShortMessage(
            String host,
            String path,
            String method,
            String appcode,
            String mobile,
            String smsSignId,
            String templateId
    ) {
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", mobile);
        //获取6位验证码
        String messageCode = getMessageAhtenticationCode(6);

        querys.put("param", "**code**:"+messageCode+",**minute**:5");

        //smsSignId（短信前缀）和templateId（短信模板），可登录国阳云控制台自助申请。参考文档：http://help.guoyangyun.com/Problem/Qm.html

        querys.put("smsSignId", smsSignId);
        querys.put("templateId", templateId);
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            StatusLine statusLine = response.getStatusLine();
            //调用状态码
            int statusCode = statusLine.getStatusCode();
            String reasonPhrase = statusLine.getReasonPhrase();

            if (200 == statusCode) {
                //发送成功后，返回验证码，存到redis中
                return ResultEntity.successWithData(messageCode);
            } else {
                return ResultEntity.failed(reasonPhrase);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }


    /**
     * 生成指定位数的随机验证码，只包含数字
     * @param length 验证码长度
     * @return
     */
    public static String getMessageAhtenticationCode(int length) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int num = (int) (Math.random() * 10);
            code.append(num);
        }
        return code.toString();
    }


    /**
     * 判断请求路径是否为静态资源路径
     * @param serveltPath
     * @return true：属于静态资源，可放行； false：需要登录后才能访问
     */
    public static boolean judgeCurrentServeltPathIsStaticResource(String serveltPath){

        if (StringUtils.isEmpty(serveltPath)){
            throw new RuntimeException(CrowdConstant.SERVELT_PATH_IS_EMPTY);
        }

        //是否属于放行的请求路径
        if (AccessPassResource.PASS_RESOURCE_SET.contains(serveltPath)){
            return true;
        }

        //是否属于static目录下的静态资源，只匹配第一层目录即可
        String[] paths = serveltPath.split("/");
        //考虑到第一个斜杠左边经过拆分后得到一个空字符串是数组的第一个元素，所以需要使用下标 1 取第二个元素
        String path = paths[1];
        if (AccessPassResource.STATIC_RESOURCE_SET.contains(path)){
            return true;
        }

        //其他就是需要登录检查的资源
        return false;
    }

}

