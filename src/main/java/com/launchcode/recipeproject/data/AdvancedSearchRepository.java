package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.AdvancedSearch;
import com.launchcode.recipeproject.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvancedSearchRepository extends CrudRepository<AdvancedSearch, Integer> {
    }
