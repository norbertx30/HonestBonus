package com.github.groupproject.repository;

import com.github.groupproject.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findOneByUuid(String userUuid);
    User findOneByEmail(String email);

    Set<User> findAllBy();
}
