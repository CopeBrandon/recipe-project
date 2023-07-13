package com.launchcode.recipeproject.models;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Tags extends AbstractEntity{

    @Size(min = 1, max = 30)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Recipe> recipes = new ArrayList<>();

    public Tags(String name) {
        this.name = name;
    }

    public Tags() {}

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
}
