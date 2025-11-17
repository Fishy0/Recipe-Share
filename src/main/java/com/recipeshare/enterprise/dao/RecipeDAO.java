package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.entity.Recipe;
import com.recipeshare.enterprise.repository.RecipeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public RecipeDTO getRecipeById(int id) {
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

    @Override
    public List<RecipeDTO> getRecipesByCategory(String category) {
        List<Recipe> recipes = repo.findByRecipeCategory(category);
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void incrementLikes(int recipeId) {
        repo.incrementLikes(recipeId);
    }

    @Override
    public List<RecipeDTO> getAllRecipesSortedByLikes() {
        List<Recipe> recipes = repo.findAllByOrderByRecipeLikesDesc();
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDTO> getRecipesByUser(String userName) {
        List<Recipe> recipes = repo.findByRecipeCreatedBy(userName);
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // DTO converter
    private RecipeDTO convertToDTO(Recipe recipe) {
        return new RecipeDTO(
                recipe.getRecipeId(),
                recipe.getRecipeCreatedBy(),
                recipe.getRecipeName(),
                recipe.getRecipeDescription(),
                recipe.getRecipeIngredients(),
                recipe.getRecipeCategory(),
                recipe.getRecipeLikes()
        );
    }

    private Recipe convertToEntity(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(dto.getRecipeId());
        recipe.setRecipeName(dto.getRecipeName());
        recipe.setRecipeCreatedBy(dto.getRecipeCreatedBy());
        recipe.setRecipeDescription(dto.getRecipeDescription());
        recipe.setRecipeIngredients(dto.getRecipeIngredients());
        recipe.setRecipeCategory(dto.getRecipeCategory());
        recipe.setRecipeLikes(dto.getRecipeLikes());
        return recipe;
    }
}

