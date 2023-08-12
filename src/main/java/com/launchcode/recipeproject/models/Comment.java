package com.launchcode.recipeproject.models;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment extends AbstractEntity {
    //TESTING
    @Size(min = 1, max = 30)
    @NotBlank
    private String name;

    @NotBlank
    private String comment;

    @ManyToOne
    private Recipe recipe;

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Comment() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return comment;
    }

}
