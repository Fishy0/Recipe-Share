package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecipeController {

    private final IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index() {
        return "Home";
    }

    @GetMapping("/getAllRecipes")
    @ResponseBody
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/getRecipeById")
    @ResponseBody
    public RecipeDTO fetchById(@RequestParam int id) {
        return recipeService.fetchById(id);
    }

    @PostMapping("/saveRecipe")
    @ResponseBody
    public String saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.saveRecipe(recipeDTO);
    }

    @DeleteMapping("/deleteRecipeById")
    @ResponseBody
    public String deleteRecipeById(@RequestParam int id) {
        return recipeService.deleteById(id);
    }
}
