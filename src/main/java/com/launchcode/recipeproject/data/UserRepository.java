package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    // query method signatures
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsById(Integer id);
}
