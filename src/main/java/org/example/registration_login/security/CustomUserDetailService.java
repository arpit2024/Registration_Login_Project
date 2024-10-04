package org.example.registration_login.security;

import org.example.registration_login.models.User;
import org.example.registration_login.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;



@Service
public class CustomUserDetailService implements UserDetailsService {
//let's inject user repository in this custom user Details service class.
//So here let me create the instance variable private user repository.

    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
    //So user name can be email or a user name. In this application we are using email so we can pass email to this application.
        User user=userRepository.findByEmail(usernameoremail);

        if(user!=null){//if we user is not null, then we'll convert this user entity to spring security
        // Provided user object. So here let's return new user object and then pass user object
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    ,user.getPassword()
                    ,user.getRoles()//we need to convert this list of roles into a list of simple granted authority objects.
                    .stream()
                    .map((role)->new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList()));
        }
        else{
            throw new UsernameNotFoundException("User not found with email: "+usernameoremail);
    /* this method loads a user object from the database and then we have converted this user into spring security provider user.
    Next, we need to set this user details service being to the authentication manager.
    For that, go to our spring security configuration class and then inject this user details service bean. */

        }





    }
}
/*if you dive into user details service interface, you can see here user detail service
interface internally provides load user by user name method.
So remember speaking security provides user details, service interface.
It has load user by user name method, and we can use this method to load a user from the database by
the given user name.*/