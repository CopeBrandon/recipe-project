package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    List<Recipe> findTop9ByOrderByIdDesc();
    Optional<Recipe> findById(Integer id);

    List<Recipe> findByUserId(Integer id);
}
