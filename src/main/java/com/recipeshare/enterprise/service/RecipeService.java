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
    public String deleteById(int id) {
        RecipeDTO recipe = recipeDAO.fetchById(id);
        if (recipe != null) {
            recipeDAO.deleteById(id);
            return "Recipe with id: " + id + " deleted";
        }
        else {
            return "Recipe with id: " + id + " not found";
        }
    }

    public String saveRecipe(RecipeDTO recipeDTO) {
        if (recipeDTO == null) {
            return "ERROR: Invalid recipe data.";
        }
        else {
            recipeDAO.saveRecipe(recipeDTO);
            return "Recipe saved successfully";
        }
    }
}

