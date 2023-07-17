package com.launchcode.recipeproject.models.dto;

import com.launchcode.recipeproject.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationDTO {

    @NotNull
    @Size(min=3, max=50, message = "Please enter a username that is between 3 to 50 characters.")
    private String username;

    @NotNull
    @Email(message = "Please enter an email address.")
    private String email;

    @NotNull
    @Size(min=8, max=50, message = "Please enter a password that is between 8 to 50 characters.")
    private String password;

    private String verifyPassword;

    // DTOs don't need a constructor for model binding

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
