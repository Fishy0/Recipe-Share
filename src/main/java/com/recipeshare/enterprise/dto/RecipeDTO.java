package com.recipeshare.enterprise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private int recipeId;
    private String recipeCreatedBy;
    private String recipeName;
    private String recipeDescription;
    private String recipeIngredients;
    private String recipeCategory;
    private int recipeLikes;
}

