package org.learning.server.service.impl

import org.learning.server.entity.*
import org.learning.server.entity.base.ChapterInfo
import org.learning.server.exception.NoAllowedException
import org.learning.server.form.CourseForm
import org.learning.server.form.ResourceForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.repository.*
import org.learning.server.service.ICourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService : ICourseService {
    //region inject components
    @Autowired
    lateinit var courseRepository: CourseRepository
    @Autowired
    lateinit var courseTagRepository: CourseTagRepository
    @Autowired
    lateinit var chapterRepository: ChapterRepository
    @Autowired
    lateinit var mediaRepository: MediaRepository
    @Autowired
    lateinit var resourceRepository: ResourceRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var messageService: MessageService
    //endregion

    //region tool functions

    private fun isCourseAdmin(course: Course, user: User): Boolean {
        return course.owner.uid == user.uid || course.adminUsers.find { it.uid == user.uid } != null
    }

    private fun guardOwner(course: Course, user: User) {
        if (course.owner.uid != user.uid) {
            throw NoAllowedException("你没有权限删除该课程")
        }
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

    // 获取与之相关的用户
    private fun getRelatedUserOfCourse(course: Course): Iterable<User> {
        val list = LinkedList<User>()
        list.add(course.owner)
        list.addAll(course.adminUsers)
        // TODO: 添加其他用户
        return list
    }
    //endregion

    //region delete operations
    private fun deleteChaptersAndResourcesByCourse(course: Course) {
        // TODO: 检查其他表的关联关系
        // 删除所有章节
        val chapters = getChapters(course)
        chapters.forEach {
            deleteMediasEntityByChapter(it)
            chapterRepository.delete(it)
        }
        // 删除所有资源
        val resources = resourceRepository.findAllByCourse(course).forEach {
            deleteResourceEntity(it)
        }
        courseRepository.delete(course)
    }

    private fun deleteResourceEntity(resource: Resource) {
        // TODO：删除文件资源
        resourceRepository.delete(resource)
    }

    private fun deleteMediasEntityByChapter(chapter: Chapter) {
        // TODO: 检查其他表的关联关系
        val medias = mediaRepository.findAllByChapter(chapter)
        medias.forEach {
            deleteMediaEntity(it)
        }
    }

    private fun deleteMediaEntity(media: Media) {
        // TODO: 检查其他表的关联关系
        mediaRepository.delete(media)
    }

    //endregion

    //region course related services

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

    override fun adminList(user: User): Iterable<Course> {
        TODO("Not yet implemented")
    }

    override fun list(user: User): Iterable<Course> {
        TODO("Not yet implemented")
    }

    override fun create(courseForm: CourseForm, user: User): Response<Course> {
        // 设置course的owner
        var course = courseForm.toCourse(user)
        // create a course
        course = courseRepository.save(course)
        return Responses.ok(course)

        // TODO: @Message 发送通知给相关的人员
    }

    override fun delete(courseId: Int, user: User): Boolean {
        val course = getCourseEntity(courseId)
        this.guardOwner(course, user)

        deleteEntity(course)
        return true
    }

    private fun deleteEntity(course: Course) {
        deleteChaptersAndResourcesByCourse(course)
        courseRepository.delete(course)
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

    override fun changeEditState(courseId: Int, edit: Boolean, user: User): Response<Course> {
        val course = getCourseEntity(courseId)
        this.guardAdmin(course, user)

        // TODO：发送状态变更的通知
        if (course.inEdit != edit) {
            this.getRelatedUserOfCourse(course).forEach {
                messageService.postCourseEditChange(course, it)
            }
        }

        return Responses.ok(course)
    }
    //endregion

    //region tag related services

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
    //endregion

    //region chapter related services

    private fun getChapters(course: Course): LinkedList<Chapter> {
        return LinkedList(chapterRepository.findAllByCourseOrderByIndexAt(course))
    }

    private fun getChapterEntity(chapterId: Int): Chapter {
        val chapterOptional = chapterRepository.findById(chapterId)
        if (chapterOptional.isEmpty) {
            throw NoAllowedException("该章节不存在")
        }
        return chapterOptional.get()
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

        deleteMediasEntityByChapter(chapterFind)

        chapters.remove(chapterFind)
        chapterRepository.delete(chapterFind)

        return Responses.ok(saveChapterOrders(chapters).map { it.toChapterInfo() })
    }
    //endregion

    //region resource related services
    private fun getResources(course: Course): Iterable<Resource> {
        return resourceRepository.findAllByCourse(course)
    }

    override fun getResources(courseId: Int, user: User): Response<Iterable<Resource>> {
        val course = getCourseEntity(courseId)
        this.guardVisit(course, user)

        return Responses.ok(getResources(course))
    }

    private fun getResourceEntity(resourceId: Int): Resource {
        val resourceOptional = resourceRepository.findById(resourceId)
        if (resourceOptional.isEmpty) {
            throw NoAllowedException("不存在该资源")
        }
        return resourceOptional.get()
    }

    override fun createResource(courseId: Int, resourceForm: ResourceForm, user: User): Response<Iterable<Resource>> {
        val course = getCourseEntity(courseId)
        this.guardAdmin(course, user)

        val resource = resourceForm.toResource(course)
        resourceRepository.save(resource)
        return Responses.ok(getResources(course))
    }

    override fun updateResource(
        courseId: Int,
        resourceId: Int,
        name: String,
        user: User
    ): Response<Iterable<Resource>> {
        val course = getCourseEntity(courseId)
        this.guardAdmin(course, user)

        val resource = getResourceEntity(resourceId)
        if (resource.course.id != courseId) {
            return Responses.fail("该资源不属于该课程，无法修改")
        }

        resource.name = name
        resourceRepository.save(resource)

        return Responses.ok(getResources(course))
    }

    override fun deleteResource(courseId: Int, resourceId: Int, user: User): Response<Iterable<Resource>> {
        val course = getCourseEntity(courseId)
        this.guardAdmin(course, user)


        val resource = getResourceEntity(resourceId)
        if (resource.course.id != courseId) {
            return Responses.fail("该资源不属于该课程，无法修改")
        }

        resourceRepository.delete(resource)

        return Responses.ok(getResources(course))
    }
    //endregion

    //region media related services
    private fun getMedias(chapter: Chapter): LinkedList<Media> {
        return LinkedList(mediaRepository.findAllByChapterOrderByIndexAt(chapter))
    }

    private fun getMediaEntity(mediaId: Int): Media {
        val mediaOptional = mediaRepository.findById(mediaId)
        if (mediaOptional.isEmpty) {
            throw NoAllowedException("不存在该media")
        }
        return mediaOptional.get()
    }


    private fun saveMediaOrders(medias: Iterable<Media>): Iterable<Media> {
        medias.forEachIndexed{
                index, media -> media.indexAt = index
        }
        return mediaRepository.saveAll(medias)
    }

    override fun getMedias(chapterId: Int, user: User): Response<Iterable<Media>> {
        val chapter = getChapterEntity(chapterId)
        this.guardVisit(chapter.course, user)

        return Responses.ok(getMedias(chapter))
    }

    override fun createMedia(
        chapterId: Int,
        name: String,
        index: Int,
        resourceId: Int,
        user: User
    ): Response<Iterable<Media>> {
        val chapter = getChapterEntity(chapterId)
        this.guardAdmin(chapter.course, user)
        val resource = getResourceEntity(resourceId)
        if (resource.course.id != chapter.course.id) {
            return Responses.fail("该资源不属于该课程，无法使用该资源")
        }

        val medias = getMedias(chapter)
        var media = Media().apply {
            this.name = name
            this.indexAt = index
            this.chapter = chapter
            this.resource = resource
        }

        media = mediaRepository.save(media)
        medias.add(index, media)

        return Responses.ok(this.saveMediaOrders(medias))
    }

    override fun updateMedia(chapterId: Int, mediaId: Int, name: String, user: User): Response<Iterable<Media>> {
        val chapter = getChapterEntity(chapterId)
        this.guardAdmin(chapter.course, user)
        val media = getMediaEntity(mediaId)
        if (media.chapter.id != chapterId) {
            return Responses.fail("该media不属于该chapter")
        }
        media.name = name
        mediaRepository.save(media)
        return Responses.ok(getMedias(chapter))
    }

    override fun moveMedia(chapterId: Int, mediaId: Int, index: Int, user: User): Response<Iterable<Media>> {
        val chapter = getChapterEntity(chapterId)
        this.guardAdmin(chapter.course, user)
        val media = getMediaEntity(mediaId)
        if (media.chapter.id != chapterId) {
            return Responses.fail("该media不属于该chapter")
        }
        val medias = getMedias(chapter)
        medias.remove(media)
        medias.add(index, media)

        return Responses.ok(saveMediaOrders(medias))
    }

    override fun deleteMedia(chapterId: Int, mediaId: Int, user: User): Response<Iterable<Media>> {
        val chapter = getChapterEntity(chapterId)
        this.guardAdmin(chapter.course, user)
        val medias = getMedias(chapter)

        val media = medias.find { it.id == mediaId }
        if (media != null) {
            medias.remove(media)
            deleteMediaEntity(media)
            return Responses.ok(saveMediaOrders(medias))
        }

        return Responses.ok(medias)
    }
    //endregion

    //region admin related services
    private fun getUserEntity(uid: String): User {
        val userOptional = userRepository.findByUid(uid)
        if (userOptional.isEmpty) {
            throw NoAllowedException("不存在当前用户")
        }
        return userOptional.get()
    }

    override fun addAdmin(courseId: Int, adminUid: String, user: User): Response<Course> {
        var course = getCourseEntity(courseId)
        this.guardOwner(course, user)
        val adminUser = getUserEntity(adminUid)
        if (course.adminUsers.find { it.uid == adminUid } == null) {
            course.adminUsers.add(adminUser)
            course = courseRepository.save(course)
        }

        return Responses.ok(course)
    }

    override fun deleteAdmin(courseId: Int, adminUid: String, user: User): Response<Course> {
        var course = getCourseEntity(courseId)
        this.guardOwner(course, user)
        val adminUser = course.adminUsers.find { it.uid == adminUid }
        if (adminUser != null) {
            course.adminUsers.remove(adminUser)
            course = courseRepository.save(course)
        }

        return Responses.ok(course)
    }

    override fun exitAdmin(courseId: Int, user: User): Response<Any> {
        var course = getCourseEntity(courseId)
        this.guardAdmin(course, user)
        val adminUser = course.adminUsers.find { it.uid == user.uid }
        if (adminUser != null) {
            course.adminUsers.remove(adminUser)
            course = courseRepository.save(course)
        }

        return Responses.ok()
    }
    //endregion
}