package com.launchcode.recipeproject.models;

import com.launchcode.recipeproject.models.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;

//public class RecipeData {


/*    public static ArrayList<Recipe> findByColumnAndValue(String column, String value){
        ArrayList<Recipe> recipe = new ArrayList<>();
        if(value. toLowerCase().equals("all")){
            return findAll();
        }
        if (column.equals("all")){
            recipes = findByValue(value);
            return recipes;

        }
        for (Recipe recipe : allRecipes){
            String aValue = getFieldValue(recipe, column);

            if (aValue !=null && aValue.toLowerCase().contains(value.toLowerCase())){
                recipe.add(recipe);
            }
        }
        return recipe;
    }
    public static String getFieldValue(Recipe recipe, String fieldName){
        String theValue;
        if(fieldName.equals("name")){
            theValue = recipe.getName();
        }else if (fieldName.equals("recentRecipes")){
            theValue = recipe.getRecentRecipes().toString();
        }else if (fieldName.equals("myFavorite")){
            theValue = recipe.getMyFavorite().toString();
        }else if (fieldName.equals("myMenu")){
            theValue = recipe.getMyMenu().toString();
        }else if (fieldName.equals("groceryList")){
            theValue = recipe.getGroceryList().toString();
        }else if (fieldName.equals("viewAll")){
            theValue = recipe.getViewAll().toString();
        }else if (fieldName.equals("advancedSearch"));
        theValue = recipe.getAdvanceSearch().toString();

return theValue;
}

    public static ArrayList<Recipe> findByValue(String value) {
        String lower_val = value.toLowerCase();

        ArrayList<Recipe> results = new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            if (recipe.getName().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getRecentRecipe().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getRecipeName().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getMyFavorite().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getMyMenu().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getGroceryList().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getViewAll().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getAdvancedSearch().toString().toLowerCase().contains(lower_val)){
                results.add(recipe);
            }
        }
        return results;
    }
}*/