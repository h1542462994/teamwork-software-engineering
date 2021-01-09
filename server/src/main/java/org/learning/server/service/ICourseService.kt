package org.learning.server.service

import org.learning.server.entity.Chapter
import org.learning.server.entity.Course
import org.learning.server.entity.CourseTag
import org.learning.server.entity.User
import org.learning.server.entity.base.ChapterInfo
import org.learning.server.form.CourseForm
import org.learning.server.model.common.Response

interface ICourseService {
    fun all(): Iterable<Course>
    fun create(courseForm: CourseForm, user: User): Response<Course>
    fun delete(courseId: Int, user: User): Boolean
    fun update(courseForm: CourseForm, user: User): Response<Course>
    fun changeEditState(courseId: Int, edit: Boolean): Response<Course>
    fun createTag(courseId: Int, name: String, user: User): Response<Iterable<CourseTag>>
    fun deleteTag(courseId: Int, tagId: Int, user: User): Response<Iterable<CourseTag>>
    fun getChapters(courseId: Int, user: User): Response<Iterable<ChapterInfo>>
    fun createChapter(courseId: Int, name: String, index: Int, user: User): Response<Iterable<ChapterInfo>>
    fun updateChapter(courseId: Int, chapterId: Int, name: String, user: User): Response<Iterable<ChapterInfo>>
    fun moveChapter(courseId: Int, chapterId: Int, index: Int, user: User): Response<Iterable<ChapterInfo>>
    fun deleteChapter(courseId: Int, chapterId: Int, user: User): Response<Iterable<ChapterInfo>>
}