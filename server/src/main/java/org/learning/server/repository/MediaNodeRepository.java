package org.learning.server.repository;

import org.learning.server.entity.CourseNode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MediaNodeRepository extends CrudRepository<CourseNode, Integer> {

    //List<CourseNode> findAllBySectionNodeId(Integer id);

}
