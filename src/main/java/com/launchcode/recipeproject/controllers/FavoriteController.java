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


    //@Autowired
    //UserRepository userRepository;

    @GetMapping("add")
    public String displayAddFavoriteForm(Model model) {
        model.addAttribute("title", "Add Favorite");
        model.addAttribute(new Favorite());
        model.addAttribute("recipes", recipeRepository.findAll());
        return "favorites/add";
    }


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Favorite Recipe");
        model.addAttribute("favorite", favoriteRepository.findAll());
        model.addAttribute("recipe", recipeRepository.findAll());
        return "favorites/index";
    }

    @PostMapping("add")
    public String processAddFavoriteForm(@ModelAttribute @Valid Favorite newFavorite,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Favorite");
            model.addAttribute("recipes", recipeRepository.findAll());
            return "favorites/add";
        }
        favoriteRepository.save(newFavorite);
        return "redirect:";

    }
}




