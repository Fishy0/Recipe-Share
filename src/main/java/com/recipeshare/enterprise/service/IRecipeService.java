package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dto.RecipeDTO;

import java.util.List;

public interface IRecipeService {
    List<RecipeDTO> getAllRecipes();
    RecipeDTO fetchById(int id);
    boolean deleteById(int id);
    RecipeDTO saveRecipe(RecipeDTO recipe);
}
