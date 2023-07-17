package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tags, Integer> {
}
