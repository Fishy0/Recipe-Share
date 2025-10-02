package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.RecipeDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeDAOStub implements IRecipeDAO {

    @Override
    public List<RecipeDTO> getAllRecipes() {

        return new ArrayList<>(List.of(
                new RecipeDTO(1, "Fruit Loops", new String[] {"Bowl"}, (short) 5),
                new RecipeDTO(2, "Frosted Flakes", new String[] {"Bowl", "milk"}, (short) 2),
                new RecipeDTO(2, "Frosted Flakes", new String[] {"Bowl", "milk", "spoon"}, (short) 3)
        ));
    }

    public RecipeDTO fetchById(int id) {
        return new RecipeDTO(
                1,
                "Fruit loops Cereal",
                new String[] {"Bowl", "milk", "Spoon"},
                (short) 5);
    }

    public void deleteById(int id) {};

    public boolean save(RecipeDTO recipe) {
        return true;
    }
}
