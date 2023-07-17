package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.Tag;
import com.launchcode.recipeproject.models.dto.RecipeIngredientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sean Feuerhelm
 */

@Controller
@RequestMapping("recipe")
public class RecipeController {

    //Repositories----------------------------------
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("create")
    public String displayCreateRecipeForm(Model model){
        model.addAttribute("title", "Create Recipe");
        model.addAttribute("form", new RecipeIngredientDTO());
        model.addAttribute("tags", tagRepository.findAll());
        return "recipe/create";
    }

    @PostMapping("create")
    public String processCreateRecipeForm(@ModelAttribute @Valid RecipeIngredientDTO form,
                                          Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Recipe");
            return "recipe/create";
        }

        //For Loop to connect all ingredient objects to the recipe objects
        for (Ingredient ingredient : form.getIngredients()){
            ingredient.setRecipe(form.getRecipe());
            form.getRecipe().addIngredient(ingredient);
        }
        //For Loop to connect the tags to the recipe
        for (Tag tag : form.getTags()){
            form.getRecipe().addTag(tag);
        }

        //Must save recipe object before ingredient due to One-to-many relationship
        recipeRepository.save(form.getRecipe());
        ingredientRepository.saveAll(form.getIngredients());


        return "redirect:view/" + form.getRecipe().getId();
    }

    @GetMapping("view/{recipeId}")
    public String displayRecipe(Model model, @PathVariable int recipeId){
        Optional optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            Recipe recipe = (Recipe)optRecipe.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("tags", tagRepository.findAll());
            model.addAttribute("title", "View Recipe");
            return "recipe/view";
        } else {
            return "redirect:../";
        }

    }
}
