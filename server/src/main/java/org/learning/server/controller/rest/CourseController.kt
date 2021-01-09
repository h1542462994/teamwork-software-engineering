package org.learning.server.controller.rest

import org.learning.server.common.SessionHelper
import org.learning.server.entity.Course
import org.learning.server.entity.CourseTag
import org.learning.server.entity.base.ChapterInfo
import org.learning.server.form.CourseForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.service.impl.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/course")
class CourseController {
    @Autowired
    lateinit var courseService: CourseService

    /**
     * 仅供测试，查找所有课程
     */
    @PostMapping("/all")
    fun all(): Response<Iterable<Course>> {
        return Responses.ok(courseService.all())
    }

    /**
     * 添加新课程
     * */
    @PostMapping("/create")
    fun create(@Valid courseForm: CourseForm, request: HttpServletRequest): Response<Course> {
        val user = SessionHelper.of(request).user()!!
        return courseService.create(courseForm, user)
    }

    /**
     * 修改课程的基础信息
     */
    @PostMapping("/update")
    fun update(@Valid courseForm: CourseForm, request: HttpServletRequest): Response<Course> {
        val user = SessionHelper.of(request).user()!!
        return courseService.update(courseForm, user)
    }

    /**
     * 在课程中添加Tag
     */
    @PostMapping("/tag/create")
    fun createTag(courseId: Int, name: String, request: HttpServletRequest): Response<Iterable<CourseTag>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.createTag(courseId, name, user)
    }

    /**
     * 在课程中移除tag
     */
    @PostMapping("/tag/delete")
    fun removeTag(courseId: Int, tagId: Int, request: HttpServletRequest): Response<Iterable<CourseTag>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.deleteTag(courseId, tagId, user)
    }

    /**
     * 在order处创建一个chapter
     */
    @PostMapping("/chapter/create")
    fun createChapter(courseId: Int, name: String, index: Int, request: HttpServletRequest): Response<Iterable<ChapterInfo>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.createChapter(courseId, name, index, user)
    }

    /**
     * 更新一个chapter的名字
     */
    @PostMapping("/chapter/update")
    fun updateChapter(courseId: Int, chapterId: Int, name: String, request: HttpServletRequest): Response<Iterable<ChapterInfo>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.updateChapter(courseId, chapterId, name, user)
    }

    /**
     * 移动一个chapter的位置
     */
    @PostMapping("/chapter/move")
    fun moveChapter(courseId: Int, chapterId: Int, index: Int, request: HttpServletRequest): Response<Iterable<ChapterInfo>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.moveChapter(courseId, chapterId, index, user)
    }

    /**
     * 删除一个chapter（包含子级media）
     */
    @PostMapping("/chapter/delete")
    fun deleteChapter(courseId: Int, chapterId: Int, request: HttpServletRequest): Response<Iterable<ChapterInfo>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.deleteChapter(courseId, chapterId, user)
    }
}