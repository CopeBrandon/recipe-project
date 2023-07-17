package com.launchcode.recipeproject.models.dto;

import com.launchcode.recipeproject.models.AbstractEntity;
import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.Tags;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean Feuerhelm
 */

public class RecipeIngredientDTO{
    @NotNull
    private Recipe recipe;

    @NotNull
    private ArrayList<Ingredient> ingredients;

    private ArrayList<Tags> tags;

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

    public ArrayList<Tags> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tags> tags) {
        this.tags = tags;
    }

    public void addTag(Tags tag){
        this.tags.add(tag);
    }
}
