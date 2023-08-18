package com.launchcode.recipeproject.controllers;


import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.RecipeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Collection;

import static com.launchcode.recipeproject.controllers.ListController.columnChoices;


//Copied everything over from ass4techjobs
@Controller
@RequestMapping
public class SearchController {

    @Autowired
    private RecipeRepository RecipeRepository;

    @RequestMapping ("adv-search")
    public String advancedSearch(Model model) {
        model.addAttribute("columns", columnChoices);
        return "adv-search";
    }


    @PostMapping ("adv-search/results")
    public String displayAdvancedSearchResults(Model model , @RequestParam String searchType,
                                               @RequestParam String searchTerm, @RequestParam String andOr) {

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        if (andOr == "and") {
            recipes = findAnd(searchType, searchTerm);
        } else {
            recipes = findOr(searchType, searchTerm);
        }

        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Recipes with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("recipes", recipes);

        return "adv-search";

    }

    private ArrayList<Recipe> findOr(String searchType, String searchTerm) {

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        String[] searchTerms = searchTerm.split(",");
        String[] searchTypes = searchType.split(",");

        for (int i=0; i < searchTerms.length; i++) {
            ArrayList<Recipe> searchResults = new ArrayList<>();
            String sTe = searchTerms[i];
            String sTy = searchTypes[i];

            if (sTe.toLowerCase().equals("all") || sTe.equals("")){
                searchResults.addAll((ArrayList<Recipe>) RecipeRepository.findAll());
            } else {
                searchResults.addAll(RecipeData.findByColumnAndValue(sTy, sTe, (ArrayList<Recipe>) RecipeRepository.findAll()));
            }

            for (int j=0; j < searchResults.size(); j++) {
                if (!recipes.contains(searchResults.get(j))) {
                    recipes.add(searchResults.get(j));
                }
            }

        }

        return recipes;

    }

    private ArrayList<Recipe> findAnd(String searchType, String searchTerm) {

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        String[] searchTerms = searchTerm.split(",");
        String[] searchTypes = searchType.split(",");

        if (searchTerms[0].toLowerCase().equals("all") || searchTerms[0].equals("")){
            recipes.addAll((ArrayList<Recipe>) RecipeRepository.findAll());
        } else {
            recipes.addAll(RecipeData.findByColumnAndValue(searchTypes[0], searchTerms[0], (ArrayList<Recipe>) RecipeRepository.findAll()));
        }

        for (int i=1; i < searchTerms.length; i++) {
            ArrayList<Recipe> searchResults = new ArrayList<>();
            String sTe = searchTerms[i];
            String sTy = searchTypes[i];

            if (sTe.toLowerCase().equals("all") || sTe.equals("")){
                searchResults.addAll((ArrayList<Recipe>) RecipeRepository.findAll());
            } else {
                searchResults.addAll(RecipeData.findByColumnAndValue(sTy, sTe, (ArrayList<Recipe>) RecipeRepository.findAll()));
            }


            for (int j=0; j < recipes.size(); j++) {
                if (!searchResults.contains(recipes.get(j))) {
                    recipes.remove(recipes.get(j));
                    j--;
                }
            }

        }

        return recipes;

    }

}