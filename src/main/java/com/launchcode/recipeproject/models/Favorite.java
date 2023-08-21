package com.launchcode.recipeproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Favorite extends AbstractEntity {

    @ManyToMany
    private final List<Favorite> favorites = new ArrayList<>();
    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

    public Favorite(User user, Recipe recipe) {
        super();
        this.user = user;
        this.recipe= recipe;
    }

    public Favorite() {

        }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }


    public void setUser(User user) {
        this.user = user;
    }
}








