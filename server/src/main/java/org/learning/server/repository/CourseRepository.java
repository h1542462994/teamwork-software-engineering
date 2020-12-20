package org.learning.server.repository;

import org.learning.server.entity.Course;
import org.learning.server.entity.CourseNode;
import org.learning.server.entity.OrgNode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    List<Course> findAll();
    List<Course> findAllByName(String name);
}
