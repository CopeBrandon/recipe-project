package com.launchcode.recipeproject.models.dto;

import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.Tag;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * Created by Sean Feuerhelm
 */

public class RecipeIngredientDTO{
    @NotNull
    private Recipe recipe;

    @NotNull
    private ArrayList<Ingredient> ingredients;

    private MultipartFile image;

    private ArrayList<Tag> tags;

    public RecipeIngredientDTO(Recipe recipe) {
        this.recipe = recipe;
        this.ingredients = new ArrayList<>();
    }

    public RecipeIngredientDTO() {}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

}
