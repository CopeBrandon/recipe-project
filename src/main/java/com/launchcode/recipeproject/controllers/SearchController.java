package com.launchcode.recipeproject.controllers;

//import com.launchcode.recipeproject.models.dto.RecipeData;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.RecipeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Scanner;

import static com.launchcode.recipeproject.controllers.ListController.columnChoices;

@Controller
@RequestMapping("Search")
public class SearchController {

    @Autowired
    private RecipeRepository recipeRepository;

    @RequestMapping ("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";

    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Recipe> recipes;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            recipes = RecipeRepository.findAll();
        } else {
            recipes = RecipeData.findByColumnAndValue(searchType, searchTerm, RecipeRepository.findAll());

        }
        model.addAttribute("columns", columnChoices);
        //model.addAttribute("recipes", recipes);
        model.addAttribute("title", "Recipes with  " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("recipes", recipes);

        return "search";


    }
}