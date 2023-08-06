package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.MyMenu;
import com.launchcode.recipeproject.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyMenuRepository extends CrudRepository<MyMenu, Integer> {
    }

