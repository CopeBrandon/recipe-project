package com.launchcode.recipeproject.models;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE recipe SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Recipe extends AbstractEntity{

    @NotBlank(message = "*Recipe name required")
    @Size(max = 75, message = "*Recipe name must not exceed 75 characters")
    private String name;

    @NotBlank(message = "*Instructions required")
    private String instructions;

    @NotNull(message = "*Portion number required")
    private Integer portionNum;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private final List<Ingredient> ingredientList = new ArrayList<>();

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    private final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/uploads/static/images/recipe";

    private final String RELATIVE_PATH = "/uploads/static/images/recipe";

    private String imagePath;

    private boolean deleted = Boolean.FALSE;

    public Recipe(String name, String instructions, Integer portionNum, User user) {
        this.name = name;
        this.instructions = instructions;
        this.portionNum = portionNum;
        this.user = user;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUPLOAD_DIRECTORY() {
        return UPLOAD_DIRECTORY;
    }

    public String getRELATIVE_PATH() {
        return RELATIVE_PATH;
    }

    public void clearIngredients(){
        this.ingredientList.clear();
    }

    public void clearTags(){
        this.tags.clear();
    }

    //Other Methods---------------------------------------------------------------


    @Override
    public String toString() {
        return name;
    }
}
