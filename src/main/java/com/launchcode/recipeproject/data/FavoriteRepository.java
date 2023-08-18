package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.Favorite;
import com.launchcode.recipeproject.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
}