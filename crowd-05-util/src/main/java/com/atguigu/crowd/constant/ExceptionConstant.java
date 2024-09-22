package com.atguigu.crowd.constant;

/**
 * @author shuyun
 * @date 2024-08-18 08:09:33
 */
public class ExceptionConstant {

    public static final String REQUEST_EXCEPTION = "exception";
    public static final String ATTR_INVALIDATE = "传入字符串为空";
    public static final String LOGIN_FAILED = "登录失败，账号或密码不正确";
    public static final String ACCESS_FORBIDDEN = "请登录后再访问";
    public static final String ACCESS_DENY= "没有权限，不允许访问";
    public static final String DOPLICATION_LOGIN_ACCOUNT = "您申请的账号已存在，请换一个";
    public static final String ACOUNT_ISNULL = "账号信息为空，请重新输入";
    public static final String PASSWORD_ISNULL = "密码为空，请重新输入";
    public static final String USERNAME_ISNULL = "用户名为空，请重新输入";
    public static final String EAMAI_ISNULL = "邮箱为空，请重新输入";
    public static final String DOPLICATION_ROLE = "该角色已经存在";

    public static final String DOPLICATION_MENU = "该菜单已经存在";
    public static final String NULL_MENU = "该菜单不存在或已经被删除";

}
