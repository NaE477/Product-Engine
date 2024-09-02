package com.portfolio.naeim.repositories;

import com.portfolio.naeim.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    User findUserByUsername(String username);
}
