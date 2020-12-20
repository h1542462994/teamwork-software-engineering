package org.learning.server.repository;

import org.learning.server.entity.CourseNode;
import org.springframework.data.repository.CrudRepository;

public interface CourseNodeRepository extends CrudRepository<CourseNode, Integer> {
}
