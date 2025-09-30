package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.IRecipeDAO;
import com.recipeshare.enterprise.dto.RecipeDTO;

import java.util.List;

public class RecipeServiceStub implements IRecipeService {

    private IRecipeDAO recipeDAO;

    List<RecipeDTO> GetAllRecipes() {

    }
    
    @Override
    public RecipeDTO fetchById(int id) {
        RecipeDTO recipeDTO = new RecipeDTO(
                1,
                "Fruit loops Cereal",
                new String[] {"Bowl", "milk", "Spoon"},
                (short) 5);
    }
    void deleteById(int id);
    RecipeDTO save(RecipeDTO recipe);

}
