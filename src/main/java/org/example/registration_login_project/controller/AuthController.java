package org.example.registration_login_project.controller;

import jakarta.validation.Valid;
import org.example.registration_login_project.dtos.UserDto;
import org.example.registration_login_project.models.User;
import org.example.registration_login_project.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //to call user service method in this controller we need to autowire the service- for registration method
    private UserService userService;


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

    //handler method to handle user registration form submit request

    @PostMapping("/register/save")
    //th:object="${user}" in the register.html file is the model attribute that we are giving here
    //@ModelAttribute is used to bind the form data with the model attribute/object
    public String registration(@Valid @ModelAttribute ("user") UserDto userDto/*pass this userDto object in to the service layer*/
            ,BindingResult result, Model model){
    //form action links that we are giving here in the form action attribute
    // in the register.html file th: command

        User existingUser =userService.findUserByEmail(userDto.getEmail());
        if(existingUser!=null){
            result.rejectValue("email",null,"There is already an account registered with that email");
        }

        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register";
        }



        userService.saveUser(userDto);
    //so once the user data is saved in Db we need to show the success message so return to the same page with the message
        return "redirect:/register?success";
        //passed the success parameter from here to the register.html file
    }
}
