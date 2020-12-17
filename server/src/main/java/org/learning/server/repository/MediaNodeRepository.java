package org.learning.server.repository;

import org.learning.server.entity.MediaNode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MediaNodeRepository extends CrudRepository<MediaNode, Integer> {

    List<MediaNode> findAllBySectionNodeId(Integer id);

}
