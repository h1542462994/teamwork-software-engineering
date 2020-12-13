package com.example.service;

import com.example.bean.Courses;

import java.util.List;

public interface CoursesService {
    List<Courses> findbyname(String username);
}
