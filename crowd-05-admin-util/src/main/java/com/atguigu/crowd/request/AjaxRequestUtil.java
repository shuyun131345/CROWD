package com.atguigu.crowd.request;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shuyun
 * @date 2024-08-17 22:20:46
 */
public class AjaxRequestUtil {

    /**
     * 本次请求是否 ajax
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        //获取请求头信息
        //ajax请求包含 application/json
        String acceptInfo = request.getHeader("Accept");

        if (!StringUtils.isEmpty(acceptInfo)){
            return acceptInfo.contains("application/json");
        }

        //ajax请求包含 XMLHttpRequest
        String xRequestedInfo = request.getHeader("X-Requested-With");
        if (!StringUtils.isEmpty(xRequestedInfo)){
            return xRequestedInfo.contains("XMLHttpRequest");
        }

        return false;
    }
}
