package com.launchcode.recipeproject.models;

import org.springframework.security.util.FieldUtils;

import java.util.ArrayList;

import static org.springframework.security.util.FieldUtils.getFieldValue;

public class RecipeData {

    private static ArrayList<Recipe> allRecipes;
    private static ArrayList<RecentRecipe> allRecentRecipes = new ArrayList<>();
    private static ArrayList<Recipe> allRecipeNames = new ArrayList<>();
    private static ArrayList<MyFavorite> allMyFavorite = new ArrayList<>();
    private static ArrayList<MyMenu> allMyMeuns = new ArrayList<>();
    private static ArrayList<GroceryList> allGroceryLists = new ArrayList<>();
    private static ArrayList<AdvancedSearch> allAdavacnedSearchs = new ArrayList<>();
    private static Object recipe;


    public static ArrayList<Recipe> findByColumnAndValue(String column, String value, Iterable<Recipe> allRecipes) throws IllegalAccessException {

          ArrayList<Recipe> results = new ArrayList<>();


            if (value.toLowerCase().equals("all")) {
                return (ArrayList<Recipe>) allRecipes;
            }
            if (column.equals("all")) {
                results = findByValue(value, allRecipes);
                return results;
            }
        for (Recipe recipe : allRecipes) {

            String aValue = String.valueOf(getFieldValue(recipe, column));

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(recipe);
            }
        }

        return results;
    }

    public static ArrayList<Recipe> findByValue(String value, Iterable<Recipe> allJobs) {
        String lower_val = value.toLowerCase();

        ArrayList<Recipe> results = new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            if (recipe.getName().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getRecentRecipes().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getMyFavorite().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getGroceryList().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getAdvanceSearch().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            }else if (recipe.getMyMenu().toString().toLowerCase().contains(lower_val)) {
                    results.add(recipe);
            } else if (recipe.toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            }

        }

        return results;
    }

    public static Iterable<Recipe> all() {
        return null;
    }
}