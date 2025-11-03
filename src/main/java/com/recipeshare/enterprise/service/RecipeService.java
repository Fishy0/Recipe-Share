package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.RecipeDAO;
import com.recipeshare.enterprise.dto.RecipeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService implements IRecipeService {

    private final RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        return recipeDAO.getAllRecipes();
    }

    @Override
    public RecipeDTO fetchById(int id){
        return recipeDAO.fetchById(id);
    }

    @Override
    public boolean deleteById(int id) {
        RecipeDTO recipe = recipeDAO.fetchById(id);
        if (recipe == null) {
            return false;
        }
        recipeDAO.deleteById(id);
        return true;
    }

    @Override
    public RecipeDTO saveRecipe(RecipeDTO recipeDTO) {
        if (recipeDTO == null) {
            throw new IllegalArgumentException("Recipe data must not be null");
        }
        return recipeDAO.saveRecipe(recipeDTO);
    }
}
