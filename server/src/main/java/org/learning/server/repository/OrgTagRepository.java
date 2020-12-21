package org.learning.server.repository;

import org.learning.server.entity.Course;
import org.learning.server.entity.OrgTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrgTagRepository extends CrudRepository<OrgTag, Integer> {
    Optional<OrgTag> findByOrgNodeId(Integer orgNodeId);
    List<OrgTag> findAll();
    List<OrgTag> findAllByName(String name);
}
