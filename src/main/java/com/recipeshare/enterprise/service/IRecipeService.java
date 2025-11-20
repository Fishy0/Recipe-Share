package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dto.RecipeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRecipeService {
    List<RecipeDTO> getAllRecipes();
    RecipeDTO getRecipeById(int id);
    String deleteById(int id);
    String saveRecipe(RecipeDTO recipe, MultipartFile recipeImage);
    List<RecipeDTO> getRecipesByCategory(String category);

    void incrementLikes(int id);

    List<RecipeDTO> getAllRecipesSortedByLikes();

    List<RecipeDTO> getRecipesByUser(String userName);
}
