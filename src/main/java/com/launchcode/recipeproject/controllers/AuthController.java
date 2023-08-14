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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String displayRegistrationForm(Model model){
        model.addAttribute("registrationDTO", new RegistrationDTO());
        model.addAttribute("title", "Register Here!");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationDTO registrationDTO, Errors errors, Model model){
        model.addAttribute("title", "Register Here!");
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

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String displayLogin(HttpServletRequest request, HttpServletResponse response, Model model){
        String path = request.getHeader("referer");
        if (!path.contains("register")) { // don't create a login/register loop
            Cookie cookie = new Cookie("referringUrl", path);
            cookie.setMaxAge(86400); // one day
            response.addCookie(cookie);
        }
        model.addAttribute("title", "Login!");
        return "login";
    }

}
