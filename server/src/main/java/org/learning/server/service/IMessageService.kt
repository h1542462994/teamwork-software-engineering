package org.learning.server.service

import org.learning.server.entity.Course
import org.learning.server.entity.User
import org.learning.server.entity.UserMessage
import org.learning.server.form.MessageForm
import org.learning.server.model.common.Response

interface IMessageService {
    /**
     * 向某用户推送一条消息
     */
    fun post(messageForm: MessageForm, user: User): Response<UserMessage>

    /**
     * 获取指定时间戳之后的所有消息
     */
    fun get(user: User, timeStamp: Long?): List<UserMessage>

    /**
     * 阅读一条消息
     */
    fun read(messageId: Int, user: User): Response<UserMessage>

    fun postCourseEditChange(course: Course, user: User)

}