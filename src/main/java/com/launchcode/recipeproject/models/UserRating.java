package com.launchcode.recipeproject.models;

import javax.persistence.Entity;

@Entity
public class UserRating extends AbstractEntity{

    private int userId;

    private Integer userRating;

    public UserRating(int userId, Integer userRating){
        this.userId = userId;
        this.userRating = userRating;
    }

    public UserRating(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }
}