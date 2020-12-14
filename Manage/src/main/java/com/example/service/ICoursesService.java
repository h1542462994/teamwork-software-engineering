package com.example.service;

import com.example.bean.Courses;
import com.example.dao.CoursesDao;
import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICoursesService implements CoursesService {

    @Autowired
    private CoursesDao coursesDao;

    @Override
    public List<Courses> findbyname(String username)
    {
        return coursesDao.findCoursesByname(username);
    }
}
