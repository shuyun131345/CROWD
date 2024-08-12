package com.atguigu.crowd.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class CrowdUtil {

    public static boolean judgeRequestType(HttpServletRequest request){

        String acceptInfo = request.getHeader("Accept");
        String requestInfo = request.getHeader("X-Requested-With");

        if (!StringUtils.isEmpty(acceptInfo)){
            return acceptInfo.contains("application/json");
        }

        if (!StringUtils.isEmpty(requestInfo)){
            return requestInfo.contains("XMLHttpRequest");
        }

        return false;
    }
}
