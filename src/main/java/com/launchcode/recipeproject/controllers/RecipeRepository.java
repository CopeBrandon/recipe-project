package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.models.Recipe;

public interface RecipeRepository {
    Iterable<Recipe> findAll();
}
