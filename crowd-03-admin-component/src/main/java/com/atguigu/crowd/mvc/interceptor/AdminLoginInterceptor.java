package com.atguigu.crowd.mvc.interceptor;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.atguigu.crowd.constant.ExceptionConstant.ACCESS_FORBIDDEN;

/**
 * @author shuyun
 * @date 2024-08-20 09:51
 * @desc
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //因为管理员登录时，会把管理员信息存到session域。所以尝试着从session域中取出来，如果不存在说明没有登录
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");

        if (Objects.isNull(admin)){
            throw new AccessForbiddenException(ACCESS_FORBIDDEN);
        }

        //返回true表示放行
        return true;

    }
}
