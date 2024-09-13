package org.example.registration_login_project.ImplService;

import org.example.registration_login_project.dtos.UserDto;
import org.example.registration_login_project.models.Role;
import org.example.registration_login_project.models.User;
import org.example.registration_login_project.repository.RoleRepository;
import org.example.registration_login_project.repository.UserRepository;
import org.example.registration_login_project.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName()+" "+userDto.getLastName());
        user.setEmail(userDto.getEmail());
        //encrypt the password using spring security-pwd Encoder than save to DB
        user.setPassword(userDto.getPassword());

        Role role = roleRepository.findByName("ROLE_ADMIN");

        if(role==null){
            role=checkRoleExist();
        }
        //add role to user object
        user.setRoles(Arrays.asList(role));
        //save user instance to DB
        userRepository.save(user);

    }
    //lets create a private method to check if role exist in DB if nor create a role and save into DB
    private Role checkRoleExist(){
        Role role=new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
