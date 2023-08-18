package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.*;
import com.launchcode.recipeproject.models.Favorite;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.RecipeData;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import static com.launchcode.recipeproject.controllers.ListController.columnChoices;

@Controller

@RequestMapping("favorites")
public class FavoriteController {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ControllerServices controllerServices;


    @Autowired
    UserRepository userRepository;

    @GetMapping("add")
    public String displayAddFavoriteForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("recipes", recipeRepository.findAll());
        return "favorites/add";
    }


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("favorite", favoriteRepository.findAll());
        return "favorites/index";
    }

    @PostMapping("add")
    public String processAddFavoriteForm(@ModelAttribute @Valid Recipe newRecipe,
                                         Errors errors, Model mode, Principal principal) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "favorites/add";
        }


        User user = controllerServices.getUser(principal);
        user.getFavRecipes().add(newRecipe);
        userRepository.save(user);
        return "favorites";
    }
    @RequestMapping(value = "recipe")
    public String listRecipesByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value){
        Iterable <Recipe> recipes;
        if (column.toLowerCase().equals("all")){
            recipes = recipeRepository.findAll();
            model.addAttribute("title", "All Recipe");
        } else {
            recipes = RecipeData.findByColumnAndValue(column, value, (ArrayList<Recipe>)(recipeRepository.findAll()));
            model.addAttribute("title", "Recipes with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("recipes", recipes);

        return "list-recipes";
    }

    }




