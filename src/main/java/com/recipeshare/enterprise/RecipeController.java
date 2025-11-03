package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDTO fetchById(@PathVariable int id) {
        return recipeService.fetchById(id);
    }

    @PostMapping
    public String saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.saveRecipe(recipeDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteRecipeById(@PathVariable int id) {
        return recipeService.deleteById(id);
    }
}
