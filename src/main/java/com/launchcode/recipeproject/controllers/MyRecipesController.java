package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.security.Principal;
import com.launchcode.recipeproject.services.ControllerServices;

@Controller
public class MyRecipesController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping("/profile/myRecipes")
    public String displayMyRecipes(Model model, Principal principal){
        User user = controllerServices.getUser(principal);
        System.out.println(user);
        int userId = user.getId();
        System.out.println(userId);
        model.addAttribute("recipes", recipeRepository.findByUserId(userId));
        System.out.println(recipeRepository.findByUserId(userId));
        return "/profile/myRecipes";
    }
}
