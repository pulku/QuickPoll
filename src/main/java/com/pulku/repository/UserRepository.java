package com.pulku.repository;

import com.pulku.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pınar on 30.03.2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
