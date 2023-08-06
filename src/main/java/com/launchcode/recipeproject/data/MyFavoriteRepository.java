package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.MyFavorite;
import com.launchcode.recipeproject.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFavoriteRepository extends CrudRepository<MyFavorite, Integer> {
    }
