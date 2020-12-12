package org.software.learning.repository;

import org.springframework.data.repository.CrudRepository;
import org.software.learning.model.entity.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUidAndPassword(String uid, String password);
}
