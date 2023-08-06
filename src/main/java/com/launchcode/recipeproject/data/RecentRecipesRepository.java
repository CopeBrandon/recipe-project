package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.RecentRecipe;
import com.launchcode.recipeproject.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentRecipesRepository extends CrudRepository<RecentRecipe, Integer> {
    }

