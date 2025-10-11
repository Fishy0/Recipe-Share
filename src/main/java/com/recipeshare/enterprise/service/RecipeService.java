package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.RecipeDAO;
import com.recipeshare.enterprise.dto.RecipeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public void deleteById(int id) {
        recipeDAO.deleteById(1);
    }

    public boolean save(RecipeDTO recipe) {
        return recipeDAO.save(recipe);
    }

}

