package org.learning.server.service.impl

import org.learning.server.entity.Course
import org.learning.server.entity.User
import org.learning.server.exception.NoAllowedException
import org.learning.server.form.CourseForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.repository.CourseRepository
import org.learning.server.service.ICourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseService : ICourseService {
    @Autowired
    lateinit var courseRepository: CourseRepository

    private fun guardAdmin(course: Course, user: User) {
        if (course.owner.uid != user.uid && course.adminUsers.find { it.uid == user.uid } == null){
            throw NoAllowedException("你没有权限修改该课程")
        }
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
        val courseOptional = courseRepository.findById(courseForm.id)
        if (courseOptional.isEmpty){
            return Responses.fail("没有指定课程")
        }

        var course = courseOptional.get()
        this.guardAdmin(course, user)
        course.updateFrom(courseForm)
        course = courseRepository.save(course)
        return Responses.ok(course)
    }
}