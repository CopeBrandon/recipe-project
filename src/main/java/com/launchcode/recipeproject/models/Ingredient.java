package com.launchcode.recipeproject.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Sean Feuerhelm
 */

@Entity
public  class Ingredient extends AbstractEntity{

    @NotBlank(message = "Ingredient names required")
    @Size(min = 1, max = 35, message = "Ingredient names must not exceed 50 characters")
    private String name;

    @NotNull(message = "Quantity required")
    private Double quantity;

    @NotBlank(message = "Measurement required")
    private String measurement;


    //Added a recipes object to link a many-to-one relationship with recipes
    @ManyToOne
    private Recipe recipe;


    public Ingredient(String name, Double quantity, String measurement, Recipe recipe) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Ingredient() {}


    //Getters and Setters ------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    //Other Methods ------------------------------------------------------

    @Override
    public String toString() {
        return name;
    }

}
