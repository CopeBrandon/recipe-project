package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.FavoriteRepository;
import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.models.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller

@RequestMapping("favorite")
public class FavoriteController {
    @Autowired
    FavoriteRepository favoriteRepository;

    @GetMapping("add")
    public String displayAddFavoriteForm(Model model) {
        model.addAttribute(new Favorite());
        return "favorite/add";
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
            return "favorite/view";
        } else {
            return "redirect:../";


        }
    }
}

