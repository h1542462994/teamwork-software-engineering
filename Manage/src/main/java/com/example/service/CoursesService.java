package main.java.com.example.service;

import main.java.com.example.bean.Courses;

import java.util.List;

public interface CoursesService {
    List<Courses> findbyname(String username);
}
