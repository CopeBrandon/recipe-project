package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.models.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String displayRegistrationForm(Model model){
        model.addAttribute("registrationDTO", new RegistrationDTO());
        model.addAttribute("title", "Registration Form");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationDTO registrationDTO, Errors errors, Model model){
        model.addAttribute("title", "Registration Form");
        if (errors.hasErrors()){
            return "register";
        }
        if(userRepository.existsByUsername(registrationDTO.getUsername())){
            errors.rejectValue("username","username.exists","Username already exists.");
            return "register";
        }
        if(!registrationDTO.getPassword().equals(registrationDTO.getVerifyPassword())){
            errors.rejectValue("password", "passwords.dontmatch","Passwords don't match.");
            return "register";
        }

        // save user to database
        String roles = "ROLE_USER";
        User user = new User(registrationDTO.getUsername(),registrationDTO.getEmail(),registrationDTO.getPassword(), roles);
        userRepository.save(user); // rewrite this section if we choose to use Roles

        return "redirect:";
    }

    @GetMapping("/login")
    public String displayLogin(Model model){
        model.addAttribute("title", "Login Form");
        return "login";
    }

}
