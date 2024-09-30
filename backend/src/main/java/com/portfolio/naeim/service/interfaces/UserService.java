package com.portfolio.naeim.service.interfaces;

import com.portfolio.naeim.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    Page<User> getUsersByPage(Pageable pageable);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    Optional<User> findByUsername(String username);
}
