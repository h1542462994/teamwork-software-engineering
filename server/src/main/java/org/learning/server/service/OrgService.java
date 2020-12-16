package org.learning.server.service;

import org.learning.server.model.Org;

import java.util.List;

public interface OrgService {
    void saveOrUpdate(Org instance);

    List<Org> getOrgs();
}
