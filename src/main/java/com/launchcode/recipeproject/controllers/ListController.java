package com.launchcode.recipeproject.controllers;



import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.RecipeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping( value="List")
public class ListController {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController(){
        columnChoices.put("all", "All");
        columnChoices.put("name", "Name");
        columnChoices.put("tags", "Tags");
        columnChoices.put("ingredients", "Ingredients");
    }
    @RequestMapping("")
    public String list(Model model){
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "List";
    }
    @RequestMapping(value = "recipe")
    public String listRecipesByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value){
        Iterable <Recipe> recipes;
        if (column.toLowerCase().equals("all")){
            recipes = recipeRepository.findAll();
            model.addAttribute("title", "All Recipe");
        } else {
            recipes = RecipeData.findByColumnAndValue(column, value, (ArrayList<Recipe>)(recipeRepository.findAll()));
            model.addAttribute("title", "Recipes with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("recipes", recipes);

        return "list-recipes";
    }

}



