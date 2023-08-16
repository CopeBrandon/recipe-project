package com.launchcode.recipeproject.models;

import java.util.*;

public class RecipeData {

// Changed Iterable to ArrayList for allRecipes
    public static ArrayList<Recipe> findByColumnAndValue(String column, String value, ArrayList<Recipe> allRecipes) {

        ArrayList<Recipe> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Recipe>) allRecipes;
        }

        if (column.equals("all")){
            results = findByValue(value, allRecipes);
            return results;
        }
        for (Recipe recipe : allRecipes) {

            String aValue = getFieldValue(recipe, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            }
        }

        return results;
    }

    public static String getFieldValue(Recipe recipe, String fieldName) {
        String theValue;
        if (fieldName.equals("name")){
            theValue = recipe.getName();
        } else if (fieldName.equals("ingredients")){
            theValue = recipe.getIngredientList().toString();
        } else {
            theValue = recipe.getTags().toString();
        }

        return theValue;
    }

    public static ArrayList<Recipe> findByValue(String value, ArrayList<Recipe> allRecipes) {
        String lower_val = value.toLowerCase();

        ArrayList<Recipe> results = new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            if (recipe.getName().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getIngredientList().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getTags().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            }

        }

        return results;
    }

}
