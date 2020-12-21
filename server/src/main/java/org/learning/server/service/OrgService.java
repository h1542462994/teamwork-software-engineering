package org.learning.server.service;

import org.learning.server.entity.Course;
import org.learning.server.entity.CourseNode;
import org.learning.server.entity.OrgNode;
import org.learning.server.model.Org;

import java.util.List;
import java.util.Optional;

public interface OrgService {
    void saveOrUpdate(Org instance);

    Optional<Org> getOrg(Integer id);

    List<Org> getOrgs();

    boolean deleteOrgs(String instance);

    boolean deleteOrgsRelation(String OrgId,String uid);


    boolean createOrgsRelationByName(String name);//由用户创建新的组织的名字

    List<OrgNode> findOrgsById(Integer id);

    List<Org> findOrgsByName(String name);
}