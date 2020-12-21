package org.learning.server.service.impl;

import org.learning.server.entity.OrgNode;
import org.learning.server.entity.OrgTag;
import org.learning.server.model.Department;
import org.learning.server.model.Org;
import org.learning.server.model.Group;
import org.learning.server.repository.OrgNodeRepository;
import org.learning.server.repository.OrgTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrgService implements org.learning.server.service.OrgService {
    OrgNodeRepository orgNodeRepository;
    OrgTagRepository orgTagRepository;

    @Autowired
    public void setOrgNodeRepository(OrgNodeRepository orgNodeRepository) {
        this.orgNodeRepository = orgNodeRepository;
    }

    @Autowired
    public void setOrgTagRepository(OrgTagRepository orgTagRepository) {
        this.orgTagRepository = orgTagRepository;
    }

    private List<OrgNode> getNeedToDeleteDepartments(Org instance) {
        List<OrgNode> savedInstances = orgNodeRepository.findAllByParentNode(instance.getId());
        Set<Integer> current = instance.getDepartments().stream()
                .map(Group::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return savedInstances
                .stream().filter((orgNode)->!current.contains(orgNode.getId())).collect(Collectors.toList());
    }

    /**
     * to save or update departments
     */
    private void saveOrUpdateDepartments(Org instance){
        int index = 0;
        List<OrgNode> needToDeleteDepartments = getNeedToDeleteDepartments(instance);
        for (Department department: instance.getDepartments()) {
            OrgNode orgNode = new OrgNode();
            orgNode.setId(department.getId());
            orgNode.setName(department.getName());
            orgNode.setDescription(department.getDescription());
            orgNode.setParentNode(instance.getId());
            orgNodeRepository.save(orgNode);
            department.setId(orgNode.getId());
            department.setTag(index++);
        }
        orgNodeRepository.deleteAll(needToDeleteDepartments);
    }

    /**
     * to save or update an org, by instance.id
     */
    @Override
    public void saveOrUpdate(Org instance) {
        OrgNode orgNode = new OrgNode();
        orgNode.setId(instance.getId());
        orgNode.setName(instance.getName());
        orgNode.setDescription(instance.getDescription());
        orgNodeRepository.save(orgNode);
        instance.setId(orgNode.getId());

        Optional<OrgTag> orgTag = orgTagRepository.findByOrgNodeId(orgNode.getId());
        if (orgTag.isPresent()) {
            instance.setTag(orgTag.get().getId());
        } else {
            OrgTag orgTag2 = new OrgTag();
            orgTag2.setOrgNodeId(orgNode.getId());
            orgTagRepository.save(orgTag2);
            instance.setTag(orgTag2.getId());
        }

        saveOrUpdateDepartments(instance);
    }

    /**
     * get the org by id
     */
    @Override
    public Optional<Org> getOrg(Integer id){
        Optional<OrgNode> orgNodeOptional = orgNodeRepository.findById(id);
        if (orgNodeOptional.isPresent() && orgNodeOptional.get().getParentNode() == null) {
            OrgNode orgNode = orgNodeOptional.get();
            Org org = new Org(orgNode.getId(), orgNode.getName(), orgNode.getDescription());
            //org.setTag(id);
            int index = 0;
            for (OrgNode node: orgNodeRepository.findAllByParentNode(id)) {
                Department department = new Department(node.getId(), node.getName(), node.getDescription());
                department.setTag(index++);
                org.addDepartment(department);
            }
            return Optional.of(org);
        } else {
            return Optional.empty();
        }
    }

    /**
     * get the org list
     */
    @Override
    public List<Org> getOrgs() {
        Iterable<OrgTag> orgTags = orgTagRepository.findAll();
        List<Org> orgs = new LinkedList<>();
        for (OrgTag tag: orgTags) {
            Optional<Org> orgOptional = getOrg(tag.getOrgNodeId());
            if (orgOptional.isPresent()) {
                orgOptional.get().setTag(tag.getId());
                orgs.add(orgOptional.get());
            }
        }
        return orgs;
    }
    /**
     * delete the org by id
     */

    @Override
    public boolean deleteOrgs(String  instance) {
        return orgNodeRepository.deleteByIdNotPersional(instance)>0;//删除部门，但是我感觉其实或许没什么用
    }

    @Override
    public boolean deleteOrgsRelation(String OrgId,String uid) {
        return orgNodeRepository.deleteById(OrgId,uid) > 0;
    }

    @Override
    public boolean createOrgsRelationByName(String name) {
        return false;
    }


    @Override
    public List<OrgNode> findOrgsById(Integer id) {
        return null;
    }

    @Override
    public List<Org> findOrgsByName(String name) {
        return null;
    }
}
