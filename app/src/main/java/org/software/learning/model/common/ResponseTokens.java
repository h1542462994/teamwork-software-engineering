package org.software.learning.model.common;

public class ResponseTokens {
    public static ResponseToken validateError = new ResponseToken(400, "validateError", "验证错误");
    public static ResponseToken ok = new ResponseToken(200, "success", "操作成功");
    public static class Login {
        public static ResponseToken registered = new ResponseToken(1, "registered", "当前用户已经被注册");
        public static ResponseToken noUser = new ResponseToken(2, "no_user", "不存在该用户");
        public static ResponseToken passwordError = new ResponseToken(3, "passwordError", "密码错误");
        public static ResponseToken noToken = new ResponseToken(4, "no_token", "不存在此token");
        public static ResponseToken tokenExpired = new ResponseToken(5, "token_expired", "token已过期");
    }
}
