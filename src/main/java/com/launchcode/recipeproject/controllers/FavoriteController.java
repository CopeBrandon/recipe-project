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
@RequestMapping("profile/favorites")
public class FavoriteController {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    RecipeRepository recipeRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping("add")
    public String displayAddFavoriteForm(Model model) {
        model.addAttribute("title", "Add Favorite");
        model.addAttribute(new Favorite());
        model.addAttribute("recipes", recipeRepository.findAll());
        return "profile/favorites/add";
    }


    @PostMapping("add")
    public String processAddFavoriteForm(@ModelAttribute @Valid Favorite newFavorite, Principal principal,
                                         Errors errors, Model model) {
        User user= controllerServices.getUser(principal);
        if (user == null) {
            model.addAttribute("title", "login");
            return "/login";
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Favorite");
            model.addAttribute("recipes", recipeRepository.findAll());
            return "profile/favorites/add";
        }
        user.addFavorite(newFavorite);
        favoriteRepository.save(newFavorite);
        return "redirect:";

    }

    @GetMapping("")
    public String displayMyFavorites(Model model, Principal principal){
        User user = controllerServices.getUser(principal);
        if (user == null) {
            model.addAttribute("title", "login");
            return "/login";
        }
        int userId = user.getId();
        model.addAttribute("title", " My Favorite Recipes");
        model.addAttribute("favorites", favoriteRepository.findByUserId(userId));
        return "profile/favorites/index";
    }
    @PostMapping("add/Favorite")
    public String addFavorite(@ModelAttribute Favorite favorite, Principal principal){
        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        Recipe recipe= favorite.getRecipe();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            favorite.setUser(user);
            favorite.setRecipe(recipe);

            recipe.getFavorites().add(favorite);
            user.getFavorites().add(favorite);

            recipeRepository.save(recipe);
            favoriteRepository.save(favorite);
            userRepository.save(user);
        }

        return "redirect:/";
    }

}






