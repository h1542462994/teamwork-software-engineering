package com.example.dao;

import com.example.bean.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CoursesDao extends JpaRepository<Courses,Integer> {
    @Modifying
    @Transactional
    @Query("select u from Courses u  where  u.name=:name")
    public List<Courses> findCoursesByname(@Param("name") String name);
}
