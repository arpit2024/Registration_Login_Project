package org.example.registration_login.services;

import org.example.registration_login.dtos.UserDto;
import org.example.registration_login.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
