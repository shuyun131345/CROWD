package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.common.AjaxResultEntity;
import com.atguigu.crowd.exception.*;
import com.atguigu.crowd.request.AjaxRequestUtil;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.atguigu.crowd.constant.ExceptionConstant.REQUEST_EXCEPTION;

/**
 * @author shuyun
 * @date 2024-08-17 22:17:29
 * 尚筹网异常信息处理类
 */
// 该注解表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView resolverException(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //其他异常处理
        String viewName = "error/system-error";
        return genExceptionResolver(exception, request, response, viewName);
    }

    /**
     * 菜单维护异常处理
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = MenuException.class)
    public ModelAndView resolverMenuException(MenuException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //菜单维护，都是ajax请求，不返回视图
        return genExceptionResolver(exception, request, response, null);
    }



    /**
     * 角色维护异常处理
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = RoleException.class)
    public ModelAndView resolverRoleException(RoleException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //角色维护，都是ajax请求，不返回视图
        return genExceptionResolver(exception, request, response, null);
    }


    @ExceptionHandler(value = EditErrorException.class)
    public ModelAndView resolverEditErrorException(EditErrorException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 返回到增加管理员界面
        String viewName = "error/system-error";
        return genExceptionResolver(exception, request, response, viewName);
    }


    /**
     * 新增管理员信息异常：账号重复，必输项为空等
     *
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = InputErrorException.class)
    public ModelAndView resolverInputErrorException(InputErrorException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 返回到增加管理员界面
        String viewName = "admin/admin-add";
        return genExceptionResolver(exception, request, response, viewName);
    }


    /**
     * 管理员未登录时抛出该异常
     *
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolverAccessForbiddenException(AccessForbiddenException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 返回到登录页面
        String viewName = "admin/admin-login";
        return genExceptionResolver(exception, request, response, viewName);
    }


    /**
     * 登录失败异常处理
     *
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 在登录页面显示异常信息
        String viewName = "admin/admin-login";
        return genExceptionResolver(exception, request, response, viewName);
    }


    /**
     * 空指针异常处理
     * 该注解表示将一个具体的异常类型和一个方法关联起来
     *
     * @param exception 实际捕获的异常对象
     * @param request   当前请求对象
     * @param response  当前响应对象
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullPointerException(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "error/system-error";
        return genExceptionResolver(exception, request, response, viewName);
    }


    /**
     * 通用异常处理类
     * 该注解表示将一个具体的异常类型和一个方法关联起来
     *
     * @param exception 实际捕获的异常对象
     * @param request   当前请求对象
     * @param response  当前响应对象
     * @param viewName  返回视图名称
     * @return
     * @throws IOException
     */
    private ModelAndView genExceptionResolver(Exception exception, HttpServletRequest request, HttpServletResponse response, String viewName) throws IOException {


        // 1.判断当前的请求类型是否是ajax请求
        boolean requestType = AjaxRequestUtil.isAjaxRequest(request);

        // 2.如果是ajax请求，就不返回页面了，返回封装后的resultEntity对象
        if (requestType) {

            // 3.从当前异常对象中获取异常信息
            String message = exception.getMessage();

            // 4.创建ajax同意返回对象ResultEntity，把请求结果和错误信息返回去
            AjaxResultEntity<Object> resultEntity = AjaxResultEntity.failed(message, null);

            // 5.ResultEntity转换成json字符串
            Gson gson = new Gson();
            String jsonResult = gson.toJson(resultEntity);

            // 6.把当前json字符串作为当前请求的响应体数据返回给浏览器
            PrintWriter writer = response.getWriter();
            writer.write(jsonResult);

            // 7.返回Null，不给springMVC提供ModelAndView对象，这样springMVC就知道不需要框架解析视图来提供响应，而是程序员自己提供了响应
            return null;

        }

        // 不是ajax请求，则返回视图
        // 8.创建ModelAndView对象
        ModelAndView view = new ModelAndView();

        // 9.将exception对象存入模型中
        view.addObject(REQUEST_EXCEPTION, exception);

        // 10.设置返回视图
        view.setViewName(viewName);
        return view;
    }


}
