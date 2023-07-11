package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String displayIndex(Model model, Principal principal){
        model.addAttribute("title", "Lets eat!");

        // example of how to bring a user in from an authenticated session
        if (principal != null){
            String username = principal.getName();
            User user = userRepository.findByUsername(username).get(); // returns optional
            System.out.println(user);
            }
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // example of method level security. must add ROLE_USER,ROLE_ADMIN manually in db
    @GetMapping("/admin")
    public String displayRestrictedIndex(Model model){
        model.addAttribute("title", "Lets eat!");
        return "admin/index";
    }
}
