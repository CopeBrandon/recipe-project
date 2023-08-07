package com.launchcode.recipeproject.models;

import org.springframework.security.util.FieldUtils;

import java.util.ArrayList;

import static org.springframework.security.util.FieldUtils.getFieldValue;

public class RecipeData {

    public static ArrayList<Recipe> findByColumnAndValue(String column, String value, Iterable<Recipe> allRecipes) {

        ArrayList<Recipe> results = new ArrayList<>();


        if (value.toLowerCase().equals("all")) {
            return (ArrayList<Recipe>) allRecipes;
        }

        if (column.equals("all")) {
            results = findByValue(value, allRecipes);
            return results;
        }

        for (Recipe recipe : allRecipes) {

            String aValue = null;
            try {
                aValue = (String) getFieldValue(recipe, column);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            }
        }

        return results;
    }

    private static ArrayList<Recipe> findByValue(String value, Iterable<Recipe> allRecipes) {



   /* public static String getFieldValue(Recipe recipe, String fieldName) {
        String theValue;
        if (fieldName.equals("name")) {
            theValue = recipe.getName();
            return theValue;
        }*/

            String lower_val = value.toLowerCase();

            ArrayList<Recipe> recipes = new ArrayList<>();

            for (Recipe recipe : allRecipes) {

                if (recipe.getName().toLowerCase().contains(lower_val)) {
                    recipes.add(recipe);

                }else if (recipe.toString().toLowerCase().contains(lower_val)) {
                    recipes.add(recipe);
                }
            }

            return recipes;
        }
    }
