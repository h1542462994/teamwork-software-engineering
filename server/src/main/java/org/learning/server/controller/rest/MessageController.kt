package org.learning.server.controller.rest

import org.learning.server.common.SessionHelper
import org.learning.server.entity.UserMessage
import org.learning.server.form.MessageForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.service.IMessageService
import org.learning.server.service.impl.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * 与消息有关的控制器
 */
@RequestMapping("/api/message")
@RestController
class MessageController {
    @Autowired
    lateinit var messageService: MessageService

    /**
     * 仅供测试，向自己推送一则消息
     */
    @PostMapping("/post")
    fun post(messageForm: MessageForm, request: HttpServletRequest): Response<UserMessage> {
        val user = SessionHelper.of(request).user()!!
        return messageService.post(messageForm, user)
    }

    @PostMapping("/get")
    fun get(timeStamp: Long?, request: HttpServletRequest): Response<List<UserMessage>> {
        val user = SessionHelper.of(request).user()!!
        return Responses.ok(messageService.get(user, timeStamp))
    }

    @PostMapping("/read")
    fun read(messageId: Int, request: HttpServletRequest): Response<UserMessage> {
        val user = SessionHelper.of(request).user()!!
        return messageService.read(messageId, user)
    }
}