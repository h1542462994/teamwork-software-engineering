package org.learning.server.repository;

import org.learning.server.entity.OrgNode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrgNodeRepository extends CrudRepository<OrgNode, Integer> {
    List<OrgNode> findAllByParentNode(Integer parentNode);
}
