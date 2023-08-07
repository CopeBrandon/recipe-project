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

            String aValue = getFieldValue(recipe, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            }
        }

        return results;
    }


    public static String getFieldValue(Recipe recipe, String fieldName) {
        String theValue;
        if (fieldName.equals("name")) {
            theValue = recipe.getName();
            return theValue;
        }

        public static ArrayList<Recipe> findByValue(String value, Iterable <Recipe> allRecipes){
            String lower_val = theValue.toLowerCase();

            ArrayList<Recipe> results = new ArrayList<>();

            for (Recipe recipe : allRecipes) {

                if (recipe.getName().toLowerCase().contains(lower_val)) {
                    results.add(recipe);

                } else if (recipe.toString().toLowerCase().contains(lower_val)) {
                    results.add(recipe);
                }
            }
            return results;
        }

    }
}