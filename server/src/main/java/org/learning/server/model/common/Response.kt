package org.learning.server.model.common

class Response<T>(var code:Int, var summary: String, var message: String, var data: T? = null) {
    constructor(token: ResponseToken, message: String, data: T? = null): this(token.code, token.summary, message, data)
    constructor(token: ResponseToken, data: T? = null): this(token.code, token.summary, token.defaultMsg, data)
}