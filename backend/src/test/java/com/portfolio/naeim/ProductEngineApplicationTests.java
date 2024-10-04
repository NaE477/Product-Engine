package com.portfolio.naeim;

import com.portfolio.naeim.dto.UserRegisterRequest;
import com.portfolio.naeim.entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

import com.portfolio.naeim.controller.UserController;
import com.portfolio.naeim.service.interfaces.UserService;

import java.util.Objects;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Transactional
class ProductEngineApplicationTests {
    private final UserController userController;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    ProductEngineApplicationTests(UserController userController, UserService userService) {
        this.userController = userController;
        this.userService = userService;
    }

    @Test
    void contextLoads() {
        // This is to verify that the context successfully loads
        assertThat(userController).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    void testUserService() {
        // Given
        User mockUser = new User();
        mockUser.setUsername("john_doe");
        mockUser.setPassword("hashed_password");
        userService.createUser(mockUser);

        // When
        var user = userService.getUserById(mockUser.getId());
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("john_doe");
    }

    @Test
    void testUserController() {
        // Example test case for UserController
        UserRegisterRequest request = new UserRegisterRequest("John Doe","john_doe", "johndoe@mail.com", "some_password", "some_password");
        var createdUser = userController.registerUser(request);

        var response = userController.getUserById(Long.valueOf(Objects.requireNonNull(createdUser.getBody()).getId()));
        assertThat(response).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getUsername()).isEqualTo("john_doe");
    }
}
