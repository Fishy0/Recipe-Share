package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dto.RecipeDTO;

import java.util.List;

public interface IRecipeService {
    List<RecipeDTO> GetAllRecipes();
    RecipeDTO fetchById(int id);
    void deleteById(int id);
    RecipeDTO save(RecipeDTO recipe);
}
