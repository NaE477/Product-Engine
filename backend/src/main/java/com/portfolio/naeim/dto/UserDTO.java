package com.portfolio.naeim.dto;

import com.portfolio.naeim.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    @NotEmpty
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;

    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
