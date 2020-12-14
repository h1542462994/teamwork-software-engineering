package com.dao;

import com.po.Courses;

import java.util.List;

public interface CoursesDao {
    public Courses selectCourseById(Integer cid);
    public List<Courses> selectAllCourses();
    public int addCourse(Courses courses);
    public int updateCourse(Courses courses);
    public int deleteCourse(Integer cid);
    public List<Courses> selectallCoursesAndPersons();
}
