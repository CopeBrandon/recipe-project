package com.launchcode.recipeproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    @Size(min = 1, max = 30)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Recipe> recipes = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {}

    public String getName() {
        return name;
    }

    public String getDisplayName(){
        return "#" + name + " ";
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe){this.recipes.add(recipe);}

    @Override
    public String toString() { return name; }

}
