package com.mvc.sell.constants;

/**
 * @author qiyichen
 * @create 2018/3/12 14:45
 */
public interface MessageConstants {

    String PWD_ERR = "密码输入错误!";
    String PWD_EMPTY = "密码不能为空";
    String USERNAME_EMPTY = "用户名不能为空";
    String TOKEN_WRONG = "请登录后操作";
    String TOKEN_EMPTY = "币种名称不能为空";
    String TITLE_EMPTY = "请填写项目标题！";
    String USER_EMPTY = "用户不存在";
    String EMAIL_NULL = "请输入邮箱！";
    String EMAIL_WRONG = "邮箱格式错误！";
    String USER_EXIST = "邮箱已注册！";
    String CODE_NULL = "请输入验证码！";
    String PASSWORD_NULL = "请输入登录密码！";
    String TRANS_PASSWORD_NULL = "请输入交易密码！";
    String TOKEN_EXPIRE = "令牌已过期,请刷新";
    Integer TOKEN_EXPIRE_CODE = 50014;
    Integer TOKEN_ERROR_CODE = 50015;
}
