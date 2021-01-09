package org.learning.server.common

import org.learning.server.entity.User
import javax.servlet.http.HttpServletRequest

class SessionHelper {
    private lateinit var request: HttpServletRequest
    fun user(): User? {
        return request.session.getAttribute("user") as User?
    }

    companion object {
        fun of(request: HttpServletRequest): SessionHelper {
            return SessionHelper().apply {
                this.request = request
            }
        }
    }
}