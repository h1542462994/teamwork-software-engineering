package org.learning.server.service.impl

import org.learning.server.entity.*
import org.learning.server.entity.base.ChapterInfo
import org.learning.server.exception.NoAllowedException
import org.learning.server.form.CourseForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.repository.ChapterRepository
import org.learning.server.repository.CourseRepository
import org.learning.server.repository.CourseTagRepository
import org.learning.server.repository.MediaRepository
import org.learning.server.service.ICourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService : ICourseService {
    @Autowired
    lateinit var courseRepository: CourseRepository
    @Autowired
    lateinit var courseTagRepository: CourseTagRepository
    @Autowired
    lateinit var chapterRepository: ChapterRepository
    @Autowired
    lateinit var mediaRepository: MediaRepository

    private fun isCourseAdmin(course: Course, user: User): Boolean {
        return course.owner.uid == user.uid || course.adminUsers.find { it.uid == user.uid } != null
    }

    private fun guardAdmin(course: Course, user: User) {
        if (!isCourseAdmin(course, user)){
            throw NoAllowedException("你没有权限修改该课程")
        }
    }

    private fun guardVisit(course: Course, user: User) {
        if (isCourseAdmin(course, user)) {
            // do Nothing
        } else {
            throw NoAllowedException("你没有权限访问chapter资源")
        }
    }

    private fun getCourseEntity(courseId: Int): Course {
        val courseOptional = courseRepository.findById(courseId)
        if (courseOptional.isEmpty) {
            throw NoAllowedException("没有该课程")
        }
        return courseOptional.get()
    }

    private fun getCourseWithGuard(courseId: Int,user: User): Course {
        val course = getCourseEntity(courseId)
        this.guardAdmin(course, user)
        return course
    }

    override fun all(): Iterable<Course> {
        return courseRepository.findAll();
    }

    override fun create(courseForm: CourseForm, user: User): Response<Course> {
        // 设置course的owner
        var course = courseForm.toCourse(user)
        // create a course
        course = courseRepository.save(course)
        return Responses.ok(course)

        // TODO: @Message 发送通知给相关的人员
    }

    override fun delete(id: Int): Boolean {
        // TODO 等待完善
        courseRepository.deleteById(id)
        return true
    }

    override fun update(courseForm: CourseForm, user: User): Response<Course> {
        if (courseForm.id == null) {
            return Responses.fail("必须指定课程id")
        }

        var course = getCourseWithGuard(courseForm.id, user)

        course.updateFrom(courseForm)
        course = courseRepository.save(course)
        return Responses.ok(course)
    }

    override fun createTag(courseId: Int, name: String, user: User): Response<Iterable<CourseTag>> {
        var course = getCourseWithGuard(courseId, user)

        val courseTagOptional = courseTagRepository.findByName(name)
        if (courseTagOptional.isEmpty){
            val courseTag = courseTagRepository.save(CourseTag().apply {
                this.name = name
            })
            course.courseTags.add(courseTag)
        } else {
            course.courseTags.add(courseTagOptional.get())
        }

        course = courseRepository.save(course)
        return Responses.ok(course.courseTags)
    }



    override fun deleteTag(courseId: Int, tagId: Int, user: User): Response<Iterable<CourseTag>> {
        var course = getCourseWithGuard(courseId, user)

        course.courseTags.removeIf { it.id == tagId }
        course = courseRepository.save(course)
        return Responses.ok(course.courseTags)
    }


    private fun getChapters(course: Course): LinkedList<Chapter> {
        return LinkedList(chapterRepository.findAllByCourseOrderByIndexAt(course))
    }

    private fun saveChapterOrders(chapters: Iterable<Chapter>): Iterable<Chapter> {
        chapters.forEachIndexed{
            index, chapter -> chapter.indexAt = index
        }
        return chapterRepository.saveAll(chapters)
    }

    override fun getChapters(courseId: Int, user: User): Response<Iterable<ChapterInfo>> {
        val course = getCourseEntity(courseId)

        this.guardVisit(course, user)

        return Responses.ok(chapterRepository.findAllByCourseOrderByIndexAt(course).map { it.toChapterInfo() })
    }

    override fun createChapter(courseId: Int, name: String, index: Int, user: User): Response<Iterable<ChapterInfo>> {
        val course = getCourseWithGuard(courseId, user)
        val chapters = getChapters(course)

        var chapter = Chapter().apply {
            this.name = name
            this.indexAt = index
            this.course = course
        }
        chapter = chapterRepository.save(chapter)
        chapters.add(index, chapter)
        return Responses.ok(saveChapterOrders(chapters).map { it.toChapterInfo() })
    }

    override fun updateChapter(
        courseId: Int,
        chapterId: Int,
        name: String,
        user: User
    ): Response<Iterable<ChapterInfo>> {
        val course = getCourseWithGuard(courseId, user)
        val chapterOptional = chapterRepository.findById(chapterId)
        if (chapterOptional.isEmpty) {
            return Responses.fail("不存在该chapter")
        }
        var chapter = chapterOptional.get()
        if (chapter.course.id != courseId){
            return Responses.fail("该chapter不属于该课程")
        }

        chapter.name = name
        chapter = chapterRepository.save(chapter)

        return Responses.ok(chapterRepository.findAllByCourseOrderByIndexAt(course).map { it.toChapterInfo() })
    }

    override fun moveChapter(courseId: Int, chapterId: Int, index: Int, user: User): Response<Iterable<ChapterInfo>> {
        val course = getCourseWithGuard(courseId, user)
        val chapters = getChapters(course)
        val chapterFind = chapters.find { it.id == chapterId } ?: return Responses.fail("该chapter不属于该课程")

        chapters.remove(chapterFind)
        chapters.add(index, chapterFind)

        return Responses.ok(saveChapterOrders(chapters).map { it.toChapterInfo() })
    }

    override fun deleteChapter(courseId: Int, chapterId: Int, user: User): Response<Iterable<ChapterInfo>> {
        val course = getCourseWithGuard(courseId, user)
        val chapters = getChapters(course)
        val chapterFind = chapters.find { it.id == chapterId } ?: return Responses.fail("该chapter不属于该课程")

        deleteMediasByChapter(chapterFind)

        chapters.remove(chapterFind)
        chapterRepository.delete(chapterFind)

        return Responses.ok(saveChapterOrders(chapters).map { it.toChapterInfo() })
    }

    private fun deleteMediasByChapter(chapter: Chapter) {
        // TODO: 检查其他表的关联关系
        val medias = mediaRepository.findAllByChapter(chapter)
        medias.forEach {
            deleteMedia(it)
        }
    }

    private fun deleteMedia(media: Media) {
        // TODO: 检查其他表的关联关系
        mediaRepository.delete(media)
    }

}