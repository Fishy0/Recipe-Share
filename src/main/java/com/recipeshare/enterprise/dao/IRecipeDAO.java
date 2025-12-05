package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.entity.Recipe;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRecipeDAO {
    List<RecipeDTO> getAllRecipes();

    RecipeDTO getRecipeById(int id);

    void updateRecipe(Recipe recipe);

    void deleteById(int id);

    Recipe saveRecipe(RecipeDTO recipe);

    List<RecipeDTO> getRecipesByCategory(String category);

    @Transactional
    void incrementLikes(int recipeId);

    List<RecipeDTO> getAllRecipesSortedByLikes();

    List<RecipeDTO> getRecipesByUser(String userName);
}
