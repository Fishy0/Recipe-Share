package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecipeController {

    private final IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String index() {
        return "Home";
    }

    @GetMapping("/getAllRecipes")
    @ResponseBody
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/getRecipeById")
    @ResponseBody
    public ResponseEntity<RecipeDTO> fetchById(@RequestParam int id) {
        RecipeDTO recipe = recipeService.fetchById(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/saveRecipe")
    @ResponseBody
    public ResponseEntity<RecipeDTO> saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        try {
            RecipeDTO savedRecipe = recipeService.saveRecipe(recipeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteRecipeById")
    @ResponseBody
    public ResponseEntity<Void> deleteRecipeById(@RequestParam int id) {
        boolean deleted = recipeService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
