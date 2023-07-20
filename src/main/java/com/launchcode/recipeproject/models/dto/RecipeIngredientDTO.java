package com.launchcode.recipeproject.models.dto;

import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * Created by Sean Feuerhelm
 */

public class RecipeIngredientDTO{
    @NotNull
    @Valid
    private Recipe recipe;

    @NotNull
    @Valid
    private ArrayList<Ingredient> ingredients;

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
