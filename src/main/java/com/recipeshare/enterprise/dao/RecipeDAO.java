package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.entity.Recipe;
import com.recipeshare.enterprise.repository.RecipeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RecipeDAO implements IRecipeDAO {

    private final RecipeRepository repo;

    public RecipeDAO(RecipeRepository repo) { this.repo = repo; }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = repo.findAll();
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public RecipeDTO fetchById(int id) {
        Optional<Recipe> recipe = repo.findById(id);
        return recipe.map(this::convertToDTO).orElse(null);
    }
    @Override
    public void saveRecipe(RecipeDTO recipe) {
        Recipe recipeEntity = convertToEntity(recipe);
        repo.save(recipeEntity);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }


    // DTO converter
    private RecipeDTO convertToDTO(Recipe recipe) {
        return new RecipeDTO(
                recipe.getRecipeId(),
                recipe.getRecipeName(),
                recipe.getRecipeDescription(),
                recipe.getRecipeIngredients(),
                recipe.getRecipeCategory(),
                recipe.getRecipeRating()
        );
    }

    private Recipe convertToEntity(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(dto.getRecipeId());
        recipe.setRecipeName(dto.getRecipeName());
        recipe.setRecipeDescription(dto.getRecipeDescription());
        recipe.setRecipeIngredients(dto.getRecipeIngredients());
        recipe.setRecipeCategory(dto.getRecipeCategory());
        recipe.setRecipeRating(dto.getRecipeRating());
        return recipe;
    }
}

