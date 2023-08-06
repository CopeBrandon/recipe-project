package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.*;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.RecipeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller@RequestMapping(value="list")
public class ListController {

    @Autowired
    private GroceryListRepository groceryListRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecentRecipesRepository recentRecipesRepository;

    @Autowired
    private MyFavoriteRepository myFavoriteRepository;

    @Autowired
    private MyMenuRepository myMenuRepository;

    @Autowired
    private AdvancedSearchRepository advancedSearchRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();


    public ListController() {
        columnChoices.put("all", "All");
        columnChoices.put("recentRecipes", "Recent Recipes");
        columnChoices.put("recipeName", "Recipe Name");
        columnChoices.put("myfavorite", "My Favorite");
        columnChoices.put("myMenu", "My Menu");
        columnChoices.put("advancedSearch", "Advanced Search");
        columnChoices.put("groceryList", "Grocery List");

    }
    @RequestMapping("")
    public String list(Model model) {
      model.addAttribute("groceryLists", groceryListRepository.findAll());
      model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("myFavorites", myFavoriteRepository.findAll());
        model.addAttribute("myMenus", myMenuRepository.findAll());
        model.addAttribute("recentRecipes", recentRecipesRepository.findAll());
        model.addAttribute("advancedSearch", advancedSearchRepository.findAll());

        return "list";
    }
    @GetMapping(value = "recipes")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Recipe> recipes;
        if (column.toLowerCase().equals("all")){
            recipes = recipeRepository.findAll();
            model.addAttribute("title", "All Recipes");
        } else {
            try {
                recipes = RecipeData.findByColumnAndValue(column, value,recipeRepository.findAll());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            model.addAttribute("title", "Recipes with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("recipes", recipes);

        return "list-recipes";
    }
}







