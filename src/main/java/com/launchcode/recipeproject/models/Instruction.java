package com.launchcode.recipeproject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Instruction extends AbstractEntity{

    @NotBlank (message = "*Instruction details required")
//    @Size(max = 255, message = "*Each step must not exceed 255 characters.")
    private String details;

    @ManyToOne (cascade = CascadeType.ALL)
    private Recipe recipe;

    public Instruction(String details) {
        this.details = details;
    }

    public Instruction() {
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Instruction that = (Instruction) o;
        return details.equals(that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), details);
    }

    @Override
    public String toString() {
        return this.details;
    }
}
