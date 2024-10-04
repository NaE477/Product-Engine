package com.portfolio.naeim.controller;

import com.portfolio.naeim.dto.UserDTO;
import com.portfolio.naeim.dto.UserRegisterRequest;
import com.portfolio.naeim.entities.User;
import com.portfolio.naeim.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.portfolio.naeim.dto.DtoMapper.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        User user = userService.createUser(userRegisterToUserEntity(request));
        return new ResponseEntity<>(toUserDTO(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsersByPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = toUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
