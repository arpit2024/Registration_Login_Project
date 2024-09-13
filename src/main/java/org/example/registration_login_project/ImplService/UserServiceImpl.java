package org.example.registration_login_project.ImplService;

import org.example.registration_login_project.dtos.UserDto;
import org.example.registration_login_project.models.Role;
import org.example.registration_login_project.models.User;
import org.example.registration_login_project.repository.RoleRepository;
import org.example.registration_login_project.repository.UserRepository;
import org.example.registration_login_project.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> userList=userRepository.findAll();
        //since users list is used we can use stream method
        return userList.stream()
                .map((user)->convertUserToUserDto(user))
                .collect(Collectors.toList());
    }

    //create private method to convert user JPA entity to userDto
    private UserDto convertUserToUserDto(User user){
        UserDto userDto=new UserDto();
        //user JPA entitiy has a name variable
        //but user Dto has Firstname and last name so we need to split it,
        String[] str=user.getName().split(" ");
        //now get both first and last name from the array
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    //let's create a private method to check if role exists in DB if not create a role and save into DB
    private Role checkRoleExist(){
        Role role=new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
