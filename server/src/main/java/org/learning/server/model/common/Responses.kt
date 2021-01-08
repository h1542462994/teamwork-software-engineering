package org.learning.server.model.common

object Responses {
    @JvmStatic
    fun <T> ok() = Response<T>(ResponseTokens.ok)
    @JvmStatic
    fun <T> ok(message: String) = Response<T>(ResponseTokens.ok, message)
    @JvmStatic
    fun <T> ok(data: T) = Response(ResponseTokens.ok, data)
    @JvmStatic
    fun <T> ok(message: String, data: T) = Response(ResponseTokens.ok, message, data)
    @JvmStatic
    fun <T> fail() = Response<T>(ResponseTokens.failed)
    @JvmStatic
    fun <T> fail(message: String) = Response<T>(ResponseTokens.failed, message)
    @JvmStatic
    fun <T> fail(data: T) = Response(ResponseTokens.failed, data)
    @JvmStatic
    fun <T> fail(message: String, data: T) = Response(ResponseTokens.failed, message, data)
    @JvmStatic
    fun <T> withToken(token: ResponseToken) = Response<T>(token)
    @JvmStatic
    fun <T> withToken(token: ResponseToken, message: String) = Response<T>(token, message)
    @JvmStatic
    fun <T> withToken(token: ResponseToken, message: String, data: T) = Response(token, message, data)
}