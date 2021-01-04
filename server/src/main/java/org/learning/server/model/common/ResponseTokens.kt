package org.learning.server.model.common

object ResponseTokens {
    val ok = ResponseToken(200, "ok", "ok")
    val validateError = ResponseToken(401, "validateError", "validateError")
    val failed = ResponseToken(403, "failed", "failed")
}