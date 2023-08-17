package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.FavoriteRepository;
import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.models.Favorite;
import com.launchcode.recipeproject.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller

@RequestMapping("favorites")
public class FavoriteController {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("add")
    public String displayAddFavoriteForm(Model model) {
        model.addAttribute("form", recipeRepository.findAll());
        model.addAttribute("recipes", recipeRepository.findAll());
        return "favorites/add";
    }


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("favorite", favoriteRepository.findAll());
        return "favorites/index";
    }

    @PostMapping("add")
    public String processAddFavoriteForm(@ModelAttribute @Valid Favorite newFavorite,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "favorites/add";
        }

        favoriteRepository.save(newFavorite);

        return "redirect:";
    }

    @GetMapping("view/{favoriteId}")
    public String displayViewFavorite(Model model, @PathVariable int favoriteId) {
        Optional optFavorite = favoriteRepository.findById(favoriteId);
        if (optFavorite.isPresent()) {
            Favorite favorite = (Favorite) optFavorite.get();
            model.addAttribute("favorite", favorite);
             model.addAttribute("recipe", recipeRepository.findAll());
            return "favorites/view";
        } else {
            return "redirect:../";


        }
    }
}

