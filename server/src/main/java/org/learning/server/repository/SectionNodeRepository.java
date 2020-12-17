package org.learning.server.repository;

import org.learning.server.entity.SectionNode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectionNodeRepository extends CrudRepository<SectionNode, Integer> {
    List<SectionNode> findAllByCourseNodeId(Integer id);
}
