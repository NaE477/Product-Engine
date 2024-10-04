package com.portfolio.naeim.service;

import com.portfolio.naeim.entities.User;
import com.portfolio.naeim.exceptions.ResourceNotFoundException;
import com.portfolio.naeim.exceptions.UserAlreadyExistsException;
import com.portfolio.naeim.repositories.UserRepository;
import com.portfolio.naeim.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getUsersByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User createUser(User user) {
        findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new UserAlreadyExistsException("Username already exists");
                });
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.getReferenceById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete, () -> {
            logger.info("User not found with id: " + id);
            throw new ResourceNotFoundException("User not found with id: " + id);
        });
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
