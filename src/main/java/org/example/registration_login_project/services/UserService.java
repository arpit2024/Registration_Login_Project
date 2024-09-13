package org.example.registration_login_project.services;

import org.example.registration_login_project.dtos.UserDto;
import org.example.registration_login_project.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
