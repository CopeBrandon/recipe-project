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

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("create")
    public String displayCreateRecipeForm(Model model){

        RecipeIngredientDTO recipeForm = new RecipeIngredientDTO();

//        while (recipeForm.getIngredients().size() > ingredientCap){
//            recipeForm.addIngredient(new Ingredient());
//        }

        model.addAttribute("form", recipeForm);



        return "recipe/create";
    }

    @PostMapping("create")
    public String processCreateRecipeForm(@ModelAttribute RecipeIngredientDTO form,
                                          Errors errors, Model model){
        if(errors.hasErrors()){
            return "recipe/create";
        }

        ingredientRepository.saveAll(form.getIngredients());
        recipeRepository.save(form.getRecipe());



        return "redirect:";
    }
}
