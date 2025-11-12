package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.RecipeDTO;

import java.util.List;

public interface IRecipeDAO {
    List<RecipeDTO> getAllRecipes();
    RecipeDTO getRecipeById(int id);
    void deleteById(int id);
    void saveRecipe(RecipeDTO recipe);

    List<RecipeDTO> getRecipesByCategory(String category);
}
