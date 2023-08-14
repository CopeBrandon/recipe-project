package com.launchcode.recipeproject.services;

import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service // creates an instance managed by spring
public class ControllerServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;


    public User getUser(Principal principal){
        if (principal == null){
            return null;
        }
        String username = principal.getName();
        Optional<User> result;
        User user;
        result = userRepository.findByUsername(username);
        // check for a normal user
        if (result.isPresent()) {
            user = result.get(); // returns User
            return user;
        }
        // check for a github oauth2 user
        result = userRepository.findByUsername(username + "@github.com");
        if (result.isPresent()) {
            user = result.get();
            return user;
        }
        // check for a google oauth2 user
        result = userRepository.findByUsername(username + "@gmail.com");
        if (result.isPresent()) {
            user = result.get();
            return user;
        }
        return null;
    }

    public Recipe getRecipe(Integer recipeId){
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            return  optRecipe.get();
        }
        return null;
    }
}
