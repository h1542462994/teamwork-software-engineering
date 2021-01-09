package org.learning.server.service.impl

import org.learning.server.entity.User
import org.learning.server.entity.UserMessage
import org.learning.server.form.MessageForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.repository.UserMessageRepository
import org.learning.server.service.IMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class MessageService: IMessageService {
    @Autowired
    lateinit var userMessageRepository: UserMessageRepository
    override fun post(messageForm: MessageForm, user: User): Response<UserMessage> {
        var userMessage = messageForm.toUserMessage(user)
        userMessage = userMessageRepository.save(userMessage)
        return Responses.ok(userMessage)
    }

    override fun get(user: User, timeStamp: Long?): List<UserMessage> {
        return if (timeStamp != null){
            userMessageRepository.findAllByUserAndCreateTimeAfter(user, Timestamp.from(Instant.ofEpochMilli(timeStamp)))
        } else {
            userMessageRepository.findAllByUser(user)
        }
    }

    override fun read(messageId: Int, user: User): Response<UserMessage> {
        val userMessageOptional = userMessageRepository.findById(messageId)
        if (userMessageOptional.isEmpty) {
            return Responses.fail("没有id为${messageId}消息")
        }
        var userMessage = userMessageOptional.get()
        if (userMessage.user.uid != user.uid) {
            return Responses.fail("没有权限阅读其他人的消息")
        }

        userMessage.isRead = true
        userMessage = userMessageRepository.save(userMessage)
        return Responses.ok(userMessage)
    }
}