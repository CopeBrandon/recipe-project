package com.launchcode.recipeproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Favorite extends AbstractEntity {

    @OneToMany
    private List<Recipe> favorites = new ArrayList<>();

    public Favorite() {

    }

    public List<Recipe> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Recipe> recipes) {
        this.favorites = favorites;
    }
}




