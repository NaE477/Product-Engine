package com.portfolio.naeim.dto;

import com.portfolio.naeim.entities.User;

public class DtoMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId().toString(), user.getName(), user.getUsername(), user.getEmail());
    }

    public static User userRegisterToUserEntity(UserRegisterRequest request) {
        return new User(request.getName(), request.getUserName(), request.getPassword(), request.getPassword());
    }
}
