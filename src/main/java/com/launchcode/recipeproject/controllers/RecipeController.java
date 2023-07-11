package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.dto.RecipeIngredientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("create")
    public String displayCreateRecipeForm(Model model){
        model.addAttribute("form", new RecipeIngredientDTO());
        return "recipe/create";
    }

    @PostMapping("create")
    public String processCreateRecipeForm(@ModelAttribute @Valid RecipeIngredientDTO form,
                                          Errors errors, Model model){
        if(errors.hasErrors()){
            return "recipe/create"; //Once more styles have been written, add styles for reloading page
        }

        //For Loop to connect all ingredient objects to the recipe objects
        for (Ingredient ingredient : form.getIngredients()){
            ingredient.setRecipe(form.getRecipe());
            form.getRecipe().addIngredient(ingredient);
        }

        //Must save recipe object before ingredient due to One-to-many relationship
        recipeRepository.save(form.getRecipe());
        ingredientRepository.saveAll(form.getIngredients());

        return "redirect:"; //Need to redirect somewhere specific, perhaps the display page when ready
    }
}
