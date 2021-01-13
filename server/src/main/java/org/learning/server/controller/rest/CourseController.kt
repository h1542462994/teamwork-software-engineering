package org.learning.server.controller.rest

import org.learning.server.common.SessionHelper
import org.learning.server.entity.*
import org.learning.server.entity.base.ChapterInfo
import org.learning.server.form.CourseForm
import org.learning.server.form.ResourceForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.CourseOpenInfo
import org.learning.server.service.ICourseService
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
    lateinit var courseService: ICourseService
    private var course: String = "course"
    /**
     * 仅供测试，查找所有课程
     */
    @PostMapping("/all")
    fun all(): Response<Iterable<Course>> {
        return Responses.ok(courseService.all())
    }

    /**
     * 获取管理的课程
     */
    @PostMapping("/list/admin")
    fun adminList(request: HttpServletRequest): Response<Iterable<Course>> {
        val user = SessionHelper.of(request).user()!!
        return Responses.ok(courseService.adminList(user))
    }

    /**
     * 获取我要上的课程
     */
    @PostMapping("/list")
    fun list(request: HttpServletRequest): Response<Iterable<CourseOpenInfo>> {
        val user = SessionHelper.of(request).user()!!
        return Responses.ok(courseService.list(user))
    }

    /**
     * 获取当前课程的基础信息
     */
    @PostMapping("/get")
    fun get(courseId: Int, request: HttpServletRequest): Response<Course> {
        val user = SessionHelper.of(request).user()!!
        return courseService.get(courseId, user)
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
     * 删除一门课程
     */
    @PostMapping("/delete")
    fun delete(courseId: Int, request: HttpServletRequest): Response<Any> {
        val user = SessionHelper.of(request).user()!!
        return Responses.ok(courseService.delete(courseId, user))
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
     * 获取一个课程的chapters
     */
    @PostMapping("/chapter/get")
    fun getChapters(courseId: Int, request: HttpServletRequest): Response<Iterable<ChapterInfo>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.getChapters(courseId, user)
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

    /**
     * 获取一个课程的resources
     */
    @PostMapping("/resource/get")
    fun getResources(courseId: Int, request: HttpServletRequest) : Response<Iterable<Resource>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.getResources(courseId, user)
    }

    /**
     * 创建一个资源
     */
    @PostMapping("/resource/create")
    fun createResource(courseId: Int, @Valid resourceForm: ResourceForm, request: HttpServletRequest): Response<Iterable<Resource>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.createResource(courseId, resourceForm, user)
    }

    /**
     * 更新一个资源
     */
    @PostMapping("/resource/update")
    fun updateResource(courseId: Int, resourceId: Int, name: String, request: HttpServletRequest): Response<Iterable<Resource>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.updateResource(courseId, resourceId, name, user)
    }

    /**
     * 删除一个资源
     */
    @PostMapping("/resource/delete")
    fun deleteResource(courseId: Int, resourceId: Int, request: HttpServletRequest): Response<Iterable<Resource>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.deleteResource(courseId, resourceId, user)
    }

    /**
     * 获取一个chapter的medias
     */
    @PostMapping("/media/get")
    fun getMedias(chapterId: Int, request: HttpServletRequest): Response<Iterable<Media>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.getMedias(chapterId, user)
    }

    /**
     * 创建一个media
     */
    @PostMapping("/media/create")
    fun createMedia(chapterId: Int, name: String, index: Int, resourceId: Int, request: HttpServletRequest): Response<Iterable<Media>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.createMedia(chapterId, name, index, resourceId, user)
    }

    /**
     * 更新一个media
     */
    @PostMapping("/media/update")
    fun updateMedia(chapterId: Int, mediaId: Int, name: String, request: HttpServletRequest): Response<Iterable<Media>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.updateMedia(chapterId, mediaId, name, user)
    }

    /**
     * 移动一个media
     */
    @PostMapping("/media/move")
    fun moveMedia(chapterId: Int, mediaId: Int, index: Int, request: HttpServletRequest): Response<Iterable<Media>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.moveMedia(chapterId, mediaId, index, user)
    }

    /**
     * 删除一个media
     */
    @PostMapping("/media/delete")
    fun deleteMedia(chapterId: Int, mediaId: Int, request: HttpServletRequest): Response<Iterable<Media>> {
        val user = SessionHelper.of(request).user()!!
        return courseService.deleteMedia(chapterId, mediaId, user)
    }

    /**
     * 向课程中添加一个管理员
     */
    @PostMapping("/admin/add")
    fun addAdmin(courseId: Int, adminUid: String, request: HttpServletRequest): Response<Course> {
        val user = SessionHelper.of(request).user()!!
        return courseService.addAdmin(courseId, adminUid, user)
    }

    /**
     * 向课程中删除一个管理员
     */
    @PostMapping("/admin/delete")
    fun deleteAdmin(courseId: Int, adminUid: String, request: HttpServletRequest): Response<Course> {
        val user = SessionHelper.of(request).user()!!
        return courseService.deleteAdmin(courseId, adminUid, user)
    }

    /**
     * 主动放弃对一个课程的管理权限
     */
    @PostMapping("/admin/exit")
    fun exitAdmin(courseId: Int, request: HttpServletRequest): Response<Any> {
        val user = SessionHelper.of(request).user()!!
        return courseService.exitAdmin(courseId, user)
    }
}