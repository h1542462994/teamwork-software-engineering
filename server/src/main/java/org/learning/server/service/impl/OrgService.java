package org.learning.server.service.impl;

import org.learning.server.entity.OrgNode;
import org.learning.server.entity.OrgTag;
import org.learning.server.model.Department;
import org.learning.server.model.Org;
import org.learning.server.model.OrgType;
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
                .map(OrgType::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return savedInstances
                .stream().filter((orgNode)->!current.contains(orgNode.getId())).collect(Collectors.toList());
    }

    private void saveOrUpdateDepartments(Org instance, boolean needCreate){
        if (needCreate) {
            int index = 0;
            for (Department department : instance.getDepartments()) {
                OrgNode orgNode = new OrgNode();
                orgNode.setName(department.getName());
                orgNode.setDescription(department.getDescription());
                orgNode.setParentNode(instance.getId());
                orgNodeRepository.save(orgNode);
                department.setId(orgNode.getId());
                department.setTag(index++);
            }
        } else {
            List<OrgNode> needToDeleteDepartments = getNeedToDeleteDepartments(instance);
            for (Department department: instance.getDepartments()) {
                OrgNode orgNode = new OrgNode();
                orgNode.setId(department.getId());
                orgNode.setDescription(department.getDescription());
                orgNode.setParentNode(instance.getId());
                orgNodeRepository.save(orgNode);
                department.setId(orgNode.getId());
            }
            orgNodeRepository.deleteAll(needToDeleteDepartments);
        }
    }

    /**
     * to save or update an org, by instance.id
     */
    @Override
    public void saveOrUpdate(Org instance) {
        boolean needCreate = false;
        if (instance.getId() == null){
            needCreate = true;
        }

        OrgNode orgNode = new OrgNode();
        orgNode.setId(instance.getId());
        orgNode.setName(instance.getName());
        orgNode.setDescription(instance.getDescription());
        orgNodeRepository.save(orgNode);
        instance.setId(orgNode.getId());

        Optional<OrgTag> orgTag = orgTagRepository.findByOrOrgNodeId(orgNode.getId());
        if (orgTag.isPresent()) {
            instance.setTag(orgTag.get().getId());
        } else {
            OrgTag orgTag2 = new OrgTag();
            orgTag2.setOrgNodeId(orgNode.getId());
            orgTagRepository.save(orgTag2);
            instance.setTag(orgTag2.getId());
        }

        saveOrUpdateDepartments(instance, needCreate);
    }

    @Override
    public List<Org> getOrgs() {
        return null;
    }
}
