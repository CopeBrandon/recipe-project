package com.launchcode.recipeproject.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Sean Feuerhelm
 */

@Entity
public class Recipe extends AbstractEntity {

    @NotBlank
    @Size(min = 1, max = 75, message = "Recipe name must not exceed 75 characters")
    private String name;

    @NotBlank
    private String instructions;

    @NotNull
    private Integer portionNum;

    @OneToMany(mappedBy = "recipe")
    private final List<Ingredient> ingredientList = new ArrayList<>();

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    private final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/uploads/static/images/recipe";

    private final String RELATIVE_PATH = "/uploads/static/images/recipe";

    private String imagePath;

    @OneToMany(cascade=CascadeType.ALL)
    private final List<UserLike> userLikes = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    private final List<UserRating> userRatings = new ArrayList<>();

    public Recipe(String name, String instructions, Integer portionNum, User user) {
        this.name = name;
        this.instructions = instructions;
        this.portionNum = portionNum;
        this.user = user;
    }

    public Recipe() {
    }


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

    public void addIngredient(Ingredient ingredient) {
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

    //Other Methods---------------------------------------------------------------

    public void handleUserLike(UserLike userLike){
        for(UserLike like : userLikes){ //remove like if present
            if (like.getUserId() == userLike.getUserId()){ //int == int
                this.userLikes.remove(userLikes.indexOf(like));
                return;
            }
        }
        this.userLikes.add(userLike); //add like if not present
    }

    public Integer userLikeCount(){
        return this.userLikes.size();
    }

    public Boolean userLiked(int userId){
        for(UserLike like : userLikes){
            if (like.getUserId() == userId){ //int == int
                return true;
            }
        }
        return false;
    }

    public List<Integer> getRatingScale() {
        return new ArrayList<>(Arrays.asList(1,2,3,4,5));
    }

    public void addUserRating(UserRating userRating){
        for(UserRating rating : userRatings){
            if (rating.getUserId()  == userRating.getUserId()){
                userRatings.set(userRatings.indexOf(rating), userRating); // update a rating
                return;
            }
        }
        this.userRatings.add(userRating); // add if not present
    }

    public double recipeRating(){
        double sum = 0.0;
        for (UserRating rating : userRatings){
            sum += rating.getUserRating();
        }
        return (double) Math.round((sum / userRatings.size()) * 10)/10; // round to one decimal place
    }

    public int userRatingCount(){
        return userRatings.size();
    }

    @Override
    public String toString() {
        return name;
    }


    }
