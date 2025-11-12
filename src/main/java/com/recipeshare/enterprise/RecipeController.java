package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

/**
    This controller provides methods for accessing and modifying Recipe data.
 */

@Controller
public class RecipeController {

    private final IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Get all Recipes from the Recipe database.
     * @return A list of Recipes.
     */
    @GetMapping("/getAllRecipes")
    @ResponseBody
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    /**
     * Get a specified Recipe object
     * @param id Unique identifier for a specific Recipe.
     * @return The Recipe object corresponding to the unique identifier provided.
     */
    @GetMapping("/getRecipeById")
    @ResponseBody
    public RecipeDTO fetchById(@RequestParam int id) {
        return recipeService.getRecipeById(id);
    }

    /**
     * Save a recipe to the database.
     * @param recipeDTO The Recipe object to save in the database.
     * @return A String stating whether the method was successful or not.
     */
    @PostMapping("/saveRecipe")
    @ResponseBody
    public String saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.saveRecipe(recipeDTO);
    }

    /**
     * Delete a recipe from the database.
     * @param id Unique identifier of the recipe to delete.
     * @return A String stating whether the method was successful or not.
     */
    @DeleteMapping("/deleteRecipeById")
    @ResponseBody
    public String deleteRecipeById(@RequestParam int id) {
        return recipeService.deleteById(id);
    }

    /**
     * Search the database for recipes of a specified category.
     * @param category The category you'd like to search for.
     * @param model model for view.
     * @return HTML page with search results.
     */
    @GetMapping("/getRecipesByCategory")
    public String getRecipesByCategory(@RequestParam String category, Model model) {
        List<RecipeDTO> recipes = recipeService.getRecipesByCategory(category);
        model.addAttribute("recipes", recipes);
        return "SearchResultsPage";
    }

    @GetMapping("/recipe/{id}")
    public String viewRecipe(@PathVariable("id") int id, Model model) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipeDetail";
    }
}
