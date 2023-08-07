package com.launchcode.recipeproject.models;

import javax.persistence.Entity;

@Entity
public class UserLike extends AbstractEntity{

    private int userId;

    public UserLike(int userId){
        this.userId = userId;
    }

    public UserLike(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}