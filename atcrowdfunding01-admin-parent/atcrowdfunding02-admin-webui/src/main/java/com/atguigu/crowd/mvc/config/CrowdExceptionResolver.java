package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.util.CrowdUtil;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//该注解表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {


    /**
     * 空指针异常处理
     * 该注解表示将一个具体的异常类型和一个方法关联起来
     * @param exception 实际捕获的异常对象
     * @param request   当前请求对象
     * @param response  当前响应对象
     * @return
     */
    @ExceptionHandler
    public ModelAndView resolverNullPointerException(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
       String viewName = "system-error";
       return genExceptionResolver(exception,request,response,viewName);

    }


    /**
     * 通用异常处理类
     * 该注解表示将一个具体的异常类型和一个方法关联起来
     * @param exception 实际捕获的异常对象
     * @param request 当前请求对象
     * @param response 当前响应对象
     * @param viewName 返回视图名称
     * @return
     * @throws IOException
     */
    @ExceptionHandler
    private ModelAndView genExceptionResolver(Exception exception, HttpServletRequest request, HttpServletResponse response, String viewName) throws IOException {


        //1.判断当前的请求类型是否是ajax请求
        boolean requestType = CrowdUtil.judgeRequestType(request);

        //2.如果是ajax请求，就不返回页面了，返回封装后的resultEntity对象
        if (requestType) {

            //3.从当前异常对象中获取异常信息
            String message = exception.getMessage();

            //4.创建ajax同意返回对象ResultEntity，把请求结果和错误信息返回去
            ResultEntity<Object> resultEntity = ResultEntity.failedWithData(message, null);

            //5.ResultEntity转换成json字符串
            Gson gson = new Gson();
            String jsonResult = gson.toJson(resultEntity);

            //6.把当前json字符串作为当前请求的响应体数据返回给浏览器
            PrintWriter writer = response.getWriter();
            writer.write(jsonResult);

            //7.返回Null，不给springMVC提供ModelAndView对象，这样springMVC就知道不需要框架解析视图来提供响应，而是程序员自己提供了响应
            return null;

        }

        //不是ajax请求，则返回视图
        //8.创建ModelAndView对象
        ModelAndView view = new ModelAndView();

        //9.将exception对象存入模型中
        view.addObject("exception", exception);

        //10.设置返回视图
        view.setViewName(viewName);
        return view;
    }
}
