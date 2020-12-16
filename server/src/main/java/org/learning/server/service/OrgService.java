package org.learning.server.service;

import org.learning.server.model.Org;

import java.util.List;
import java.util.Optional;

public interface OrgService {
    void saveOrUpdate(Org instance);

    Optional<Org> getOrg(Integer id);

    List<Org> getOrgs();
}
