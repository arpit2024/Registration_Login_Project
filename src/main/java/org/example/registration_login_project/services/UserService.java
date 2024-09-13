package org.example.registration_login_project.services;

import org.example.registration_login_project.dtos.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
}
