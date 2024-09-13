package org.example.registration_login_project.dtos;


//inorder to store the registration form data we need to have a model object
//Model class to store form data

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email cannot be Empty")
    @Email
    private String email;
    @NotEmpty(message = "Password cannot be Empty")
    private String password;

}
