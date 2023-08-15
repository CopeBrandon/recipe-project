package com.launchcode.recipeproject.services;

import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            result = userRepository.findByUsername(username);
            // check for a normal user
            if (result.isPresent()) {
                user = result.get(); // returns User
                return user;
            }
        }
        if (principal instanceof OAuth2AuthenticationToken) {
            // check for a github oauth2 user
            if (((OAuth2AuthenticationToken) principal).getAuthorizedClientRegistrationId().equals("github")) {
                result = userRepository.findByUsername(username + "@github.com");
                if (result.isPresent()) {
                    user = result.get();
                    return user;
                }
            }
            // check for a google oauth2 usergit 
            if (((OAuth2AuthenticationToken) principal).getAuthorizedClientRegistrationId().equals("google")) {
                result = userRepository.findByUsername(username + "@gmail.com");
                if (result.isPresent()) {
                    user = result.get();
                    return user;
                }
            }
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
    public String randomString(int n){
        String baseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        String returnString = "";
        while (returnString.length() < n){
            int index = (int) Math.round((baseString.length()-1) * Math.random());
            returnString += baseString.charAt(index);
        }
        return returnString;
    }
}
