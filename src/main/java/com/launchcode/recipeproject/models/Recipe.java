package com.launchcode.recipeproject.models;


import org.springframework.core.metrics.StartupStep;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean Feuerhelm
 */

@Entity
public class Recipe extends AbstractEntity{

    @NotBlank
    @Size(min = 1, max = 75, message = "Recipe name must not exceed 75 characters")
    private String name;

    @NotBlank
    private String instructions;

    @NotNull
    private Integer portionNum;

    @OneToMany(mappedBy = "recipe")
    private final List<Ingredient> ingredientList = new ArrayList<>();

    @ManyToMany(mappedBy = "recipes")
    private final List<Tags> tags = new ArrayList<>();

    public Recipe(String name, String instructions, Integer portionNum) {
        this.name = name;
        this.instructions = instructions;
        this.portionNum = portionNum;
    }

    public Recipe() {}


    //Getters and Setters----------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getPortionNum() {
        return portionNum;
    }

    public void setPortionNum(Integer portionNum) {
        this.portionNum = portionNum;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredientList.add(ingredient);
    }

    //Other Methods---------------------------------------------------------------


    @Override
    public String toString() {
        return name;
    }
}
