package com.atguigu.crowd.constant;

/**
 * 常量类
 */
public class CrowdConstant {

    //验证码存入到redis中的key的前缀
    public static final String REDIS_MESSAGE_CODE_PREFIX = "REDIS_MESSAGE_CODE_PREFIX_";

    public static final String REGISTER_CHECK = "register_check";
    public static final String REQUEST_IS_EMPTY = "输入参数为空";
    public static final String PHONENUM_IS_EMPTY = "手机号为空";
    public static final String LOGINACCT_IS_EMPTY = "输入账号为空";
    public static final String PASD_IS_EMPTY = "输入密码为空";
    public static final String MESSAGE_CODE_IS_EMPTY = "输入验证码为空";
    public static final String REGISTER_FAIL = "注册失败，请稍后再试";
    public static final String MESSAGE_CODE_ERROR = "验证码错误，请重新输入";
    public static final String LOGINACCT_IS_EXIST = "该账号已存在，请重新输入";
    public static final String LOGINACCT_IS_NOT_EXIST = "该账号不存在，请重新输入";
    public static final String LOGIN_CHECK = "login_check";
    public static final String LOGIN_FAIL = "登录失败，请稍后再试";
    public static final String PSWD_ERROR = "输入的密码不正确";
    public static final String LOGIN_MEMBER = "loginMember";
}
