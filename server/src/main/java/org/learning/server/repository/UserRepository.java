package org.learning.server.repository;

import org.learning.server.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUid(String uid);
    Integer deleteByUid(String uid);
}
