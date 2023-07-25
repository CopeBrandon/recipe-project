package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.services.ControllerServices;
import com.launchcode.recipeproject.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping()
    public String displayIndex(Model model, Principal principal){
        model.addAttribute("title", "Lets eat!");

        model.addAttribute("top9", recipeRepository.findTop9ByOrderByIdDesc());

        // example of how to bring a user in from an authenticated session
        //TODO remove later, this is just an example
        User user = controllerServices.getUser(principal); // get back User or null
        System.out.println(user);

        return "index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // example of method level security. must add ROLE_USER,ROLE_ADMIN manually in db
    @GetMapping("/admin")
    public String displayRestrictedIndex(Model model){
        model.addAttribute("title", "Recipe Refresh!");
        return "admin/index";
    }
}
