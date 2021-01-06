package org.learning.server.entity.base

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Id

open class UserBase {
    open var uid: String = ""
    open var name: String = ""
    open var password: String = ""
    open var age: Int = 1
    open var sex: Boolean = false
    open var email: String = ""
}