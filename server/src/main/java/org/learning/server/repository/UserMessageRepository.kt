package org.learning.server.repository

import org.learning.server.entity.User
import org.learning.server.entity.UserMessage
import org.springframework.data.repository.CrudRepository
import java.sql.Timestamp

interface UserMessageRepository: CrudRepository<UserMessage, Int> {
    fun findAllByUserAndCreateTimeAfter(user: User, createTime: Timestamp): List<UserMessage>
    fun findAllByUser(user: User): List<UserMessage>
}