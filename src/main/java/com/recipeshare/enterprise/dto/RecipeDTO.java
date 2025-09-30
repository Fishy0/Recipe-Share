package com.recipeshare.enterprise.dto;

public class RecipeDTO {
    int recipeId;
    String recipeName;
    String[] recipeIngredients;
    short recipeRating;

    public RecipeDTO(int recipeId, String recipeName, String[] recipeIngredients, short recipeRating) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeRating = recipeRating;
    }
}

