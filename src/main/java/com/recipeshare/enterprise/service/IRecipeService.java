package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dto.RecipeDTO;

import java.util.List;

public interface IRecipeService {
    List<RecipeDTO> getAllRecipes();
    RecipeDTO fetchById(int id);
    String deleteById(int id);
    String saveRecipe(RecipeDTO recipe);

    List<RecipeDTO> getRecipesByCategory(String category);
}
