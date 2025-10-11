package com.recipeshare.enterprise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private String recipeIngredients;
    private String recipeCategory;
    private short recipeRating;
}

