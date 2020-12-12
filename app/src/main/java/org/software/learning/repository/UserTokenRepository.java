package org.software.learning.repository;

import org.software.learning.model.entity.UserToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTokenRepository extends CrudRepository<UserToken, Integer> {
    Optional<UserToken> findByUidAndToken(String uid, String token);
}
