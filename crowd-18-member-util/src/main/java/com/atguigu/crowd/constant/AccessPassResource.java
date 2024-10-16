package com.atguigu.crowd.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 存放不需要登录检查的静态资源，在zuul工程中放行这些资源
 */
public class AccessPassResource {

    //不需登录检查就放行的访问路径
    public static final Set<String> PASS_RESOURCE_SET = new HashSet<>();

    //不需登录检查就放行的静态资源
    public static final Set<String> STATIC_RESOURCE_SET = new HashSet<>();

    static {
        //在静态代码块中完成初始化
        PASS_RESOURCE_SET.add("/"); //首页
        PASS_RESOURCE_SET.add("/auth/member/register"); //从首页跳转到注册页
        PASS_RESOURCE_SET.add("/auth/member/login"); //从首页跳转到登录界面
        PASS_RESOURCE_SET.add("/auth/member/sendShortMessage"); //注册时发送验证码到手机
        PASS_RESOURCE_SET.add("/auth/member/do/register"); //执行注册
        PASS_RESOURCE_SET.add("/auth/member/do/login"); //执行登录
        PASS_RESOURCE_SET.add("/auth/member/logout"); //退出登录
    }


    static {
        //static目录下的资源，只需要匹配第一层目录即可
        STATIC_RESOURCE_SET.add("bootstrap");
        STATIC_RESOURCE_SET.add("css");
        STATIC_RESOURCE_SET.add("fonts");
        STATIC_RESOURCE_SET.add("img");
        STATIC_RESOURCE_SET.add("jquery");
        STATIC_RESOURCE_SET.add("layer");
        STATIC_RESOURCE_SET.add("script");
        STATIC_RESOURCE_SET.add("ztree");
    }
}
