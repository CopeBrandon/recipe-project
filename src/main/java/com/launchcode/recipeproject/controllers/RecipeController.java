package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.*;
import com.launchcode.recipeproject.models.*;
import com.launchcode.recipeproject.models.dto.RecipeIngredientDTO;
import com.launchcode.recipeproject.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
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
    //TESTING
    @Autowired
    private CommentRepository commentRepository;

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
    public String processCreateRecipeForm(@ModelAttribute @Valid RecipeIngredientDTO form,
                                          Errors errors, Model model, Principal principal) throws IOException {
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

        // Add image path to Recipe and save the image
        String absolutePath = form.getRecipe().getUPLOAD_DIRECTORY() + form.getImage().getOriginalFilename();
        Files.write(Path.of((absolutePath)), form.getImage().getBytes()); // write image to hard drive
        form.getRecipe().setImagePath(form.getRecipe().getRELATIVE_PATH() + form.getImage().getOriginalFilename());

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

    //TESTING
    @PostMapping("view/{recipeId}")
    public String processRecipe(Model model, @RequestParam(required=true) String commentName,
                                @RequestParam(required=true) String commentComment, @PathVariable int recipeId){
        Optional optRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe)optRecipe.get();
        Comment comment = new Comment();

        if (commentName != "" || commentComment != "") {
            comment.setName(commentName);
            comment.setComment(commentComment);
            comment.setRecipe(recipe);

            commentRepository.save(comment);
        }

        model.addAttribute("recipe", recipe);
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("title", "View Recipe");

        return "recipe/view";
    }
}
