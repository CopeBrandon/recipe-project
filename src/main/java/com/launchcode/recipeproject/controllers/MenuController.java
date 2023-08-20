package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import com.launchcode.recipeproject.services.ControllerServices;

@Controller
public class MenuController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping("/profile/menu")
    public String displayMenu(Model model, Principal principal){
        User user = controllerServices.getUser(principal);

        if (user == null) {
            model.addAttribute("title", "login");
            return "/login";
        }

        List<Recipe> menuRecipes = user.getMenuRecipes();
        model.addAttribute("menuRecipes", menuRecipes);
        return "/profile/menu";
    }
}

