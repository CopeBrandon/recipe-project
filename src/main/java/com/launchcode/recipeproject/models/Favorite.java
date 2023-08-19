package com.launchcode.recipeproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Favorite extends AbstractEntity {

    @ManyToOne
    private Recipe recipe;

    public Favorite(Recipe recipe) {
        super();
        this.recipe = recipe;

    }

    public Favorite() {

        }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}








