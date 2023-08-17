package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.*;
import com.launchcode.recipeproject.data.*;
import com.launchcode.recipeproject.exceptions.ResourceNotFoundException;
import com.launchcode.recipeproject.models.*;
import com.launchcode.recipeproject.data.*;
import com.launchcode.recipeproject.models.*;
import com.launchcode.recipeproject.models.dto.RecipeIngredientDTO;
import com.launchcode.recipeproject.services.ControllerServices;
import com.launchcode.recipeproject.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
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
    private InstructionRepository instructionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping("create")
    public String displayCreateRecipeForm(Model model){
        model.addAttribute("title", "Create Recipe");
        model.addAttribute("form", new RecipeIngredientDTO());
        model.addAttribute("tags", tagRepository.findAll());
        return "recipe/create";
    }

    @PostMapping("create")
    public String processCreateRecipeForm(@ModelAttribute ("form") @Valid RecipeIngredientDTO form,
                                          Errors errors, Model model, Principal principal) throws IOException{
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Recipe");
            model.addAttribute("form", form);
            model.addAttribute("tags", tagRepository.findAll());
            return "recipe/create";
        }

        //For Loop to connect all ingredient objects to the recipe objects
        for (Ingredient ingredient : form.getIngredients()){
            ingredient.setRecipe(form.getRecipe());
            form.getRecipe().addIngredient(ingredient);
        }

        for (Instruction instruction : form.getInstructions()){
            instruction.setRecipe(form.getRecipe());
            form.getRecipe().addInstruction(instruction);
        }

        //For Loop to connect the tags to the recipe
        ArrayList<Tag> allTags = (ArrayList<Tag>) tagRepository.findAll();
        if (allTags.size() > 0){
            for (Tag tag : form.getTags()){
                form.getRecipe().addTag(tag);
            }
        }

        User user = controllerServices.getUser(principal); // this will probably cause an error if the user is not signed in. this path will eventually be restricted to users.
        form.getRecipe().setUser(user);
        user.addRecipe(form.getRecipe());

        // Add image path to Recipe and save the image
        if(form.getImage().getSize() != 0) { // check if image was uploaded
            String imageName = controllerServices.randomString(25) + "_" + form.getImage().getOriginalFilename();
            String absolutePath = form.getRecipe().getUPLOAD_DIRECTORY() + imageName;
            Files.write(Path.of((absolutePath)), form.getImage().getBytes()); // write image to hard drive
            form.getRecipe().setImagePath(form.getRecipe().getRELATIVE_PATH() + imageName); // set image path in Recipe
        } else{
            form.getRecipe().setImagePath("/images/placeholder.jpg");

        }

        //Must save recipe object before ingredient due to One-to-many relationship
        recipeRepository.save(form.getRecipe());
        ingredientRepository.saveAll(form.getIngredients());
        instructionRepository.saveAll(form.getInstructions());
        userRepository.save(user);

        return "redirect:view/" + form.getRecipe().getId();
    }

    @GetMapping("view/{recipeId}")
    public String displayRecipe(Model model, @PathVariable int recipeId, Principal principal){
        Optional optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            Recipe recipe = (Recipe)optRecipe.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("tags", tagRepository.findAll());
            model.addAttribute("title", recipe.getName() + " - Recipe Refresh");
            model.addAttribute("user", controllerServices.getUser(principal));
            return "recipe/view";
        } else {
            model.addAttribute("title", "Recipe does not exist"); //TODO place holder for title
            return "recipe/notFound";
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
            editForm.setInstructions(recipeToEdit.getInstructions());

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

        if(errors.hasErrors()){
            model.addAttribute("title", "Edit Recipe");
            model.addAttribute("editForm", recipeDetails);
            model.addAttribute("tags", tagRepository.findAll());
            return "recipe/edit";
        }

        //Check if entry is in database, if not, throws exception
        Optional optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            //Converts optional into a recipe object
            Recipe recipeToEdit = (Recipe)optRecipe.get();

            //Separates DTO into more readable objects
            Recipe editedRecipe = recipeDetails.getRecipe();
            List<Ingredient> editedIngs = recipeDetails.getIngredients();
            List<Instruction> editedInsts = recipeDetails.getInstructions();

            //Uses getters and setter to update the original database object
            recipeToEdit.setName(editedRecipe.getName());
            recipeToEdit.setPortionNum(editedRecipe.getPortionNum());


            //For each loop for updating each individual ingredient with getters and setters
            int indexIng = 0;
            for (Ingredient ing : recipeToEdit.getIngredientList()) {
                if (indexIng == editedIngs.size()){
                    recipeToEdit.getIngredientList().remove(indexIng);
                    break;
                } else {
                    Optional optIng = ingredientRepository.findById(ing.getId());
                    if (optIng.isPresent()) {
                        Ingredient ingToEdit = (Ingredient) optIng.get();
                        ingToEdit.setName(editedIngs.get(indexIng).getName());
                        ingToEdit.setMeasurement(editedIngs.get(indexIng).getMeasurement());
                        ingToEdit.setQuantity(editedIngs.get(indexIng).getQuantity());
                        indexIng++;
                    }
                }
            }

            //Adds newly created ingredients to the main entity to be saved
            for (Ingredient newIng : editedIngs) {
                boolean found = false;
                for (Ingredient oldIng : recipeToEdit.getIngredientList()) {
                    if (newIng.getName().equals(oldIng.getName())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    newIng.setRecipe(recipeToEdit);
                    recipeToEdit.addIngredient(newIng);
                }
            }

            //Updates existing instructions
            int indexInst = 0;
            for (Instruction inst : recipeToEdit.getInstructions()){
                if (indexInst == editedInsts.size()){
                    recipeToEdit.getInstructions().remove(indexInst);
                    break;
                } else {
                    Optional optInst = instructionRepository.findById(inst.getId());
                    if (optInst.isPresent()) {
                        Instruction instToEdit = (Instruction) optInst.get();
                        instToEdit.setDetails(editedInsts.get(indexInst).getDetails());
                        indexInst++;
                    }
                }
            }

            //adds new instructions
            for (Instruction newInst : editedInsts) {
                boolean found = false;
                for (Instruction oldInst : recipeToEdit.getInstructions()) {
                    if (newInst.getDetails().equals(oldInst.getDetails())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    newInst.setRecipe(recipeToEdit);
                    recipeToEdit.addInstruction(newInst);
                }
            }

            //Clear existing tags before Adding the updated tags
            recipeToEdit.clearTags();
            if (recipeDetails.getTags() != null) {
                for (Tag tag : recipeDetails.getTags()) {
                    recipeToEdit.addTag(tag);
                }
            }

            //Saves the updated recipe
            recipeRepository.save(recipeToEdit);

            //redirects you to the updated view page
            return "redirect:../view/" + recipeId;
        } else {
           throw new ResourceNotFoundException("No recipe exists with the id: " + recipeId);
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

    @GetMapping("delete/{recipeId}")
    private String deleteRecipe (@PathVariable int recipeId, Model model){
        Optional optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            recipeRepository.deleteById(recipeId);
        }
        model.addAttribute("title", "Recipe does not exist"); //TODO place holder for title
        return "recipe/notFound";
    }

    @PostMapping("convert/{recipeId}")
    private String convertRecipe (@PathVariable int recipeId,
                                  @ModelAttribute("portionNum") int newPortionNum,
                                  Model model){
        Optional optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            Recipe convertedRecipe = (Recipe) optRecipe.get();
            int oldPortionNum = convertedRecipe.getPortionNum();
            for (Ingredient ingredient : convertedRecipe.getIngredientList()){
                double convertedIngQuantity = (ingredient.getQuantity() / oldPortionNum) * newPortionNum;
                ingredient.setQuantity(convertedIngQuantity);
                ingredient.convertMeasurement();
            }
            convertedRecipe.setPortionNum(newPortionNum);
            model.addAttribute("recipe", convertedRecipe);
            model.addAttribute("title", "Converted Recipe");
            model.addAttribute("tags", tagRepository.findAll());
            return "recipe/view";
        }
        model.addAttribute("title", "Recipe does not exist"); //TODO place holder for title
        return "recipe/notFound";
    }


    @GetMapping("view/{recipeId}/like")
    public String processUserLike(@PathVariable int recipeId, Principal principal){
        Recipe recipe = controllerServices.getRecipe(recipeId); // returns a Recipe or null
        User user = controllerServices.getUser(principal); // returns a User or null
        UserLike userLike = new UserLike(user.getId()); // generate like
        recipe.handleUserLike(userLike); // like or unlike
        recipeRepository.save(recipe);

        return "redirect:/recipe/view/" + recipe.getId();
    }

    @GetMapping("view/{recipeId}/rate")
    public String processUserRating(@PathVariable int recipeId, @RequestParam String rating, Principal principal){
        Recipe recipe = controllerServices.getRecipe(recipeId);
        User user = controllerServices.getUser(principal);
        UserRating userRating = new UserRating(user.getId(), Integer.parseInt(rating));
        recipe.addUserRating(userRating); // add or change rating
        recipeRepository.save(recipe);

        return "redirect:/recipe/view/" + recipe.getId();
    }
}

