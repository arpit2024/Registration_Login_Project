package org.example.registration_login_project.controller;

import org.example.registration_login_project.dtos.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    //handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    //handler method to handle user Registration form request

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        //create a model attribute to bind/store form data
        UserDto user=new UserDto();
        model.addAttribute("user",user);
        return "register";
    }


}
