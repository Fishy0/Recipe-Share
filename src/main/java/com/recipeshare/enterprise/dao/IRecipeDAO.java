package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.entity.Recipe;

import java.util.List;

public interface IRecipeDAO {
    List<RecipeDTO> getAllRecipes();
    RecipeDTO fetchById(int id);
    void deleteById(int id);
    RecipeDTO saveRecipe(RecipeDTO recipe);
}
