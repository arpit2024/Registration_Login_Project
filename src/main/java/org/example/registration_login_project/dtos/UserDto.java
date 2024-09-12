package org.example.registration_login_project.dtos;


//inorder to stroe the registration form data we need to have a model object
//Model class to store form data

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
