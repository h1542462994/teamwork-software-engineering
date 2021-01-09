package org.learning.server.model.common

object ResponseTokens {
    val ok = ResponseToken(200, "ok", "ok")
    val validateError = ResponseToken(400, "validateError", "validateError")
    val failed = ResponseToken(403, "failed", "failed")

    object User {
        val noLogin = ResponseToken(1, "noLogin", "未登录无法访问该资源")
        var forbidden = ResponseToken(401, "forbidden", "没有足够的权限")
    }
}