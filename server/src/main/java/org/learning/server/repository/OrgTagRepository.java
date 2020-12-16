package org.learning.server.repository;

import org.learning.server.entity.OrgTag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrgTagRepository extends CrudRepository<OrgTag, Integer> {
    Optional<OrgTag> findByOrOrgNodeId(Integer orgNodeId);
}
