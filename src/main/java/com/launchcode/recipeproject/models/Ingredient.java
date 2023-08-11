package com.launchcode.recipeproject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Sean Feuerhelm
 */

@Entity
public class Ingredient extends AbstractEntity{

    @NotBlank(message = "*Ingredient name required")
    @Size(max = 35, message = "*Ingredient names must not exceed 50 characters")
    private String name;

    @NotNull(message = "*Quantity required")
    private Double quantity;

    @NotBlank(message = "*Measurement required")
    private String measurement;


    //Added a recipes object to link a many-to-one relationship with recipes
    @ManyToOne (cascade = CascadeType.ALL)
    private Recipe recipe;


    public Ingredient(String name, Double quantity, String measurement) {
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

    public Ingredient convertMeasurement(){

        //converts drops/dashes into tsp. approx. 100 drops go into 1 tsp.
        if (this.measurement.equals("drop") || this.measurement.equals("dash") && this.quantity > 50){
            this.quantity = this.quantity / 100;
            this.measurement = "tsp.";
        }

        //converts tsp to tbsp
        if (this.measurement.equals("tsp.") && this.quantity > 3){
            this.quantity = this.quantity / 3;
            this.measurement =  "tbsp.";
        }

        //converts tbsp to cups
        if (this.measurement.equals("tbsp.") && this.quantity > 8){
            this.quantity = this.quantity / 16;
            this.measurement = "cup";
        }

        this.quantity = (Math.round(this.quantity*100.00)) / 100.00;

        return this;
    }

    @Override
    public String toString() {
        return name;
    }

}
