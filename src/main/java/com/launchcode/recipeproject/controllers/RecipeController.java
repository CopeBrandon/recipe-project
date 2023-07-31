package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.exceptions.ResourceNotFoundException;
import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.Tag;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.models.dto.RecipeIngredientDTO;
import com.launchcode.recipeproject.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JpaUserDetailsService jpaUserDetailsService;

    @GetMapping("create")
    public String displayCreateRecipeForm(Model model){
        model.addAttribute("title", "Create Recipe");
        model.addAttribute("form", new RecipeIngredientDTO());
        model.addAttribute("tags", tagRepository.findAll());
        return "recipe/create";
    }

    @PostMapping("create")
    public String processCreateRecipeForm(@ModelAttribute ("form") @Valid RecipeIngredientDTO form,
                                          Errors errors, Model model, Principal principal){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Recipe");
            model.addAttribute("form", form);
            model.addAttribute("tags", tagRepository.findAll());
            for (Ingredient ingredient : form.getIngredients()){
                if (ingredient.getName().isBlank() || ingredient.getMeasurement().isBlank() || ingredient.getQuantity() == null){
                    model.addAttribute("ingError", "Ensure all ingredient fields are entered correctly");
                    break;
                }
            }
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

        //Get user information and set it in the recipe
        User user; //TODO create a fake user until we turn on security
        Optional<User> result = userRepository.findByUsername("Temp_User");
        if (result.isPresent()){user = result.get();}
        else{user = new User("Temp_User", "Temp_User_Email@none.com", "Temp_Pass", "ROLE_USER"); userRepository.save(user);}
//        if (principal != null){ // TODO uncomment when security is turned on
//            System.out.println(principal.getName());
//            User user = jpaUserDetailsService.getUsername(principal.getName()); // send username, get back User or null
//        }

        form.getRecipe().setUser(user);
        user.addRecipe(form.getRecipe());

        //Must save recipe object before ingredient due to One-to-many relationship
        recipeRepository.save(form.getRecipe());
        ingredientRepository.saveAll(form.getIngredients());
        userRepository.save(user);

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

    //Editing Controllers ------------------------------------------------------

    @GetMapping("edit/{recipeId}")
    public String displayEditForm (Model model, @PathVariable int recipeId){

        Optional optRecipe = recipeRepository.findById(recipeId);

        if (optRecipe.isPresent()){
            Recipe recipeToEdit = (Recipe)optRecipe.get();
            RecipeIngredientDTO editForm = new RecipeIngredientDTO();
            editForm.setRecipe(recipeToEdit);
            editForm.setTags(recipeToEdit.getTags());
            editForm.setIngredients(recipeToEdit.getIngredientList());

            model.addAttribute("editForm", editForm);
            model.addAttribute("tags", tagRepository.findAll());
            model.addAttribute("title", "Edit Recipe");
            return "recipe/edit";
        } else {
            throw new ResourceNotFoundException("No recipe exists with the id: " + recipeId);
        }
    }

    @PostMapping("edit/{recipeId}")
    public String updateRecipe(@ModelAttribute ("editForm") @Valid RecipeIngredientDTO recipeDetails,
                               Errors errors, Model model,
                               @PathVariable int recipeId){

        //Validates that the updated info doesn't have errors
        //TODO Currently only redirects, does not display error messages
        if(errors.hasErrors()){
            model.addAttribute("title", "Edit Recipe");
            model.addAttribute("editForm", recipeDetails);
            model.addAttribute("tags", tagRepository.findAll());
            return "redirect:/recipe/edit/" + recipeId;
        }

        //Check if entry is in database, if not, throws exception
        Optional optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            //Converts optional into a recipe object
            Recipe recipeToEdit = (Recipe)optRecipe.get();

            //Separates DTO into more readable objects
            Recipe editedRecipe = recipeDetails.getRecipe();
            List<Ingredient> editedIngs = recipeDetails.getIngredients();

            //Uses getters and setter to update the original database object
            recipeToEdit.setName(editedRecipe.getName());
            recipeToEdit.setInstructions(editedRecipe.getInstructions());
            recipeToEdit.setPortionNum(editedRecipe.getPortionNum());

            //For each loop for updating each individual ingredient with getters and setters
            int index = 0;
            for (Ingredient ing : recipeToEdit.getIngredientList()) {
                Optional optIng = ingredientRepository.findById(ing.getId());
                if (optIng.isPresent()) {
                    Ingredient ingToEdit = (Ingredient)optIng.get();
                    ingToEdit.setName(editedIngs.get(index).getName());
                    ingToEdit.setMeasurement(editedIngs.get(index).getMeasurement());
                    ingToEdit.setQuantity(editedIngs.get(index).getQuantity());
                    index++;
                } else {
                    throw new ResourceNotFoundException("No ingredient exists with the id: " + ing.getId());
                }
            }

            //Clear existing tags before Adding the updated tags
            recipeToEdit.clearTags();
            for (Tag tag : recipeDetails.getTags()){
                recipeToEdit.addTag(tag);
            }

            //Saves the updated ingredients and recipe
            recipeRepository.save(recipeToEdit);
            ingredientRepository.saveAll(recipeToEdit.getIngredientList());

            //redirects you to the updated view page
            return "redirect:/recipe/view/" + recipeId;
        } else {
           throw new ResourceNotFoundException("No recipe exists with the id: " + recipeId);
        }
    }
}
