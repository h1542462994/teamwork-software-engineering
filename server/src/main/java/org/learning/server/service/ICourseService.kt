package org.learning.server.service

import org.learning.server.entity.*
import org.learning.server.entity.base.ChapterInfo
import org.learning.server.form.CourseForm
import org.learning.server.form.ResourceForm
import org.learning.server.model.common.Response
import org.learning.server.model.complex.CourseOpenInfo

interface ICourseService {
    fun all(): Iterable<Course>

    /**
     * 获取自己管理课程的列表
     */
    fun adminList(user: User): Iterable<Course>
    fun list(user: User): Iterable<CourseOpenInfo>
    fun get(courseId: Int, user: User): Response<Course>
    fun create(courseForm: CourseForm, user: User): Response<Course>
    fun delete(courseId: Int, user: User): Boolean
    fun update(courseForm: CourseForm, user: User): Response<Course>
    fun changeEditState(courseId: Int, edit: Boolean, user: User): Response<Course>
    fun createTag(courseId: Int, name: String, user: User): Response<Iterable<CourseTag>>
    fun deleteTag(courseId: Int, tagId: Int, user: User): Response<Iterable<CourseTag>>
    fun getChapters(courseId: Int, user: User): Response<Iterable<ChapterInfo>>
    fun createChapter(courseId: Int, name: String, index: Int, user: User): Response<Iterable<ChapterInfo>>
    fun updateChapter(courseId: Int, chapterId: Int, name: String, user: User): Response<Iterable<ChapterInfo>>
    fun moveChapter(courseId: Int, chapterId: Int, index: Int, user: User): Response<Iterable<ChapterInfo>>
    fun deleteChapter(courseId: Int, chapterId: Int, user: User): Response<Iterable<ChapterInfo>>
    fun getResources(courseId: Int, user: User): Response<Iterable<Resource>>
    fun createResource(courseId: Int, resourceForm: ResourceForm, user: User): Response<Iterable<Resource>>
    fun updateResource(courseId: Int, resourceId: Int, name: String, user: User): Response<Iterable<Resource>>
    fun deleteResource(courseId: Int, resourceId: Int, user: User): Response<Iterable<Resource>>
    fun getMedias(chapterId: Int, user: User): Response<Iterable<Media>>
    fun createMedia(chapterId: Int, name: String, index: Int, resourceId: Int, user: User): Response<Iterable<Media>>
    fun updateMedia(chapterId: Int, mediaId: Int, name: String, user: User): Response<Iterable<Media>>
    fun moveMedia(chapterId: Int, mediaId: Int, index: Int, user: User): Response<Iterable<Media>>
    fun deleteMedia(chapterId: Int, mediaId: Int, user: User): Response<Iterable<Media>>
    fun addAdmin(courseId: Int, adminUid: String, user: User): Response<Course>
    fun deleteAdmin(courseId: Int, adminUid: String, user: User): Response<Course>
    fun exitAdmin(courseId: Int, user: User): Response<Any>

}