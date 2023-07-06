package com.launchcode.recipeproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean Feuerhelm
 */

@Entity
public class Ingredient extends AbstractEntity{

    /**
     * Gave ingredients a name and tspQuantity (Tablespoon Quantity)
     * I also included a constructor, as well as getters and setters.
     */

    @NotBlank(message = "Ingredient names required")
    @Size(min = 1, max = 35, message = "Ingredient names must not exceed 50 characters")
    private String name;

    @NotNull(message = "Quantity Required")
    private Double tspQuantity;


    //Added a recipes array to link a many-to-many relationship with recipes
    @ManyToMany(mappedBy = "ingredientsList")
    private List<Recipe> recipes = new ArrayList<>();


    public Ingredient(String name, Double tspQuantity) {
        this.name = name;
        this.tspQuantity = tspQuantity;
    }

    public Ingredient() {}


    //Getters and Setters ------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTspQuantity() {
        return tspQuantity;
    }

    public void setTspQuantity(Double tspQuantity) {
        this.tspQuantity = tspQuantity;
    }

    //Other Methods ------------------------------------------------------

    @Override
    public String toString() {
        return name;
    }

    public Double toTableSpoons(){
        Double teaspoons = this.tspQuantity;
        return (teaspoons / 3);
    }

    public Double toCups(){
        Double teaspoons = this.tspQuantity;
        return (teaspoons / 48);
    }

    public Double toOunces(){
        Double teaspoons = this.tspQuantity;
        return (teaspoons / 6);
    }

    public Double toDrops(){
        Double teaspoons = this.tspQuantity;
        return (teaspoons * 8);
    }
}
