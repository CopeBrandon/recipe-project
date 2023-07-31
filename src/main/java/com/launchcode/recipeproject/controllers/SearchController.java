package com.launchcode.recipeproject.controllers;


import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.RecipeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.launchcode.recipeproject.controllers.ListController.columnChoices;


//Copied everything over from ass4techjobs
@Controller
@RequestMapping
public class SearchController {

    @Autowired
    private RecipeRepository RecipeRepository;

    @RequestMapping ("search")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping ("search/results")
    public String displaySearchResults(Model model , @RequestParam String searchType, @RequestParam String searchTerm) {

        Iterable<Recipe> recipes;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            recipes = RecipeRepository.findAll();
        } else {
            recipes = RecipeData.findByColumnAndValue(searchType, searchTerm, RecipeRepository.findAll());
        }

        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Recipes with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("recipes", recipes);

        return "search";

    }

    @RequestMapping ("adv-search")
    public String advancedSearch(Model model) {
        model.addAttribute("columns", columnChoices);
        return "adv-search";
    }


    @PostMapping ("adv-search/results")
    public String displayAdvancedSearchResults(Model model , @RequestParam String searchType, @RequestParam String searchTerm) {

        Iterable<Recipe> recipes = null;
        String[] searchTerms = searchTerm.split(",");
        String[] searchTypes = searchType.split(",");
        System.out.println(Arrays.toString(searchTerms));
        System.out.println(Arrays.toString(searchTypes));

        for (int i=0; i < searchTerms.length; i++) {
            String sTe = searchTerms[i];
            String sTy = searchTypes[i];

            if (sTe.toLowerCase().equals("all") || sTe.equals("")){
                recipes = RecipeRepository.findAll();
            } else {
                recipes = RecipeData.findByColumnAndValue(sTy, sTe, RecipeRepository.findAll());
            }

        }

        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Recipes with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("recipes", recipes);

        return "adv-search";

    }

}