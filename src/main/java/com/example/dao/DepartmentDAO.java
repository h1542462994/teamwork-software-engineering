package main.java.com.example.dao;

import main.java.com.example.bean.Department;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface DepartmentDAO extends JpaRepository<Department,Integer>{
	@Modifying
    @Transactional
    @Query("select u from Courses u  where  u.name=:name")
    public List<Courses> findCoursesByname(@Param("name") String name);
}
