package com.launchcode.recipeproject.models.dto;

import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.Tag;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean Feuerhelm
 */

public class RecipeIngredientDTO{
    @NotNull
    @Valid
    private Recipe recipe;

    @NotNull
    @Valid
    private List<Ingredient> ingredients;

    private List<Tag> tags;

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public List<Tag> getTags() {
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
}
