package com.launchcode.recipeproject.controllers;



import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.RecipeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.HashMap;



//Copied everything over from ass3techjobs
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private RecipeRepository recipeRepository;


    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("name", "Name");
        columnChoices.put("ingredients", "Ingredients");
        columnChoices.put("tags", "Tags");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("tags", tagRepository.findAll());

        return "list";
    }

//    @GetMapping(value = "jobs")
//    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
//
//        Iterable<Recipe> recipes;
//        if (column.toLowerCase().equals("all")){
//            recipes = recipeRepository.findAll();
//            model.addAttribute("title", "All Jobs");
//        } else {
//            recipes = RecipeData.findByColumnAndValue(column, value, recipeRepository.findAll());
//            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
//        }
//        model.addAttribute("recipes", recipes);
//
//        return "list-recipes";
//    }
}