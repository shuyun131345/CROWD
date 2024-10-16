package com.atguigu.crowd.filter;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.util.CrowdUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
public class CrowdAccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //这里返回“pre”意思是在目标微服务前执行过滤
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //1.获取 RequestContext 对象
        RequestContext currentContext = RequestContext.getCurrentContext();

        //2.通过 RequestContext 对象获取当前请求对象（框架底层是借助ThreadLocal 从当前线程上获取事先绑定的 Request 对象）
        HttpServletRequest request = currentContext.getRequest();

        //3.获取请求路径serveltPath
        String servletPath = request.getServletPath();

        // 4.根据 servletPath 判断当前请求是否对应可以直接放行的特定功能
        boolean isStaticResource = CrowdUtil.judgeCurrentServeltPathIsStaticResource(servletPath);
        if (isStaticResource) {
            //如果属于静态资源或者放行的资源，则返回false放行
            return false;
        }
        //返回true表示执行登录检查
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //在auth工程登录的时候，密码校验通过后，会将MemberLoginVo对象存入到Session域中
        //在使用了SpringSession后，Session域的数据实际是存到redis中，所以可以在zuul工程中获取到
        //session.setAttribute(CrowdConstant.LOGIN_MEMBER,memberLoginVo);
        //尝试从Session域中获取登录对象
        HttpSession session = request.getSession();
        Object memberLoginVo = session.getAttribute(CrowdConstant.LOGIN_MEMBER);

        if (Objects.isNull(memberLoginVo)){
            //如果为空，则说明未登录，将提示消息存入到Session域，重定向到登录页面
            session.setAttribute(CrowdConstant.LOGIN_CHECK,CrowdConstant.ACCESS_FORBIDEN);
            HttpServletResponse response = currentContext.getResponse();
            try {
                response.sendRedirect("/auth/member/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
