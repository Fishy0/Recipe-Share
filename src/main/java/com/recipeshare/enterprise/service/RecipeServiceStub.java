package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.RecipeDAOStub;
import com.recipeshare.enterprise.dto.RecipeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceStub implements IRecipeService {

    RecipeDAOStub recipeDAOStub = new RecipeDAOStub();

    @Override
    public List<RecipeDTO> getAllRecipes() {
        return recipeDAOStub.GetAllRecipes();
    }

    @Override
    public RecipeDTO fetchById(int id){
        return recipeDAOStub.fetchById(1);
    }
    @Override
    public void deleteById(int id) {
        recipeDAOStub.deleteById(1);
    }

    public boolean save(RecipeDTO recipe) {
        recipe = new RecipeDTO(55, "Cornflakes", new String[] {"sugar", "bleach", "gatorade"}, (short) 5);
        return recipeDAOStub.save(recipe);
    }

}
