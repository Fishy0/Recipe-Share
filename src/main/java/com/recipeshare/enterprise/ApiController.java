package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import com.recipeshare.enterprise.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final IRecipeService recipeService;
    private final IUserService userService;

    public ApiController(IRecipeService recipeService, IUserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    // ---------- API calls for recipes. ----------
    /**
     * Get all Recipes from the Recipe database.
     * @return A list of Recipes.
     */
    @GetMapping("/recipes")
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    /**
     * Get a specified Recipe object
     * @param id Unique identifier for a specific Recipe.
     * @return The Recipe object corresponding to the unique identifier provided.
     */
    @GetMapping("/recipes/{id}")
    public RecipeDTO getRecipeById(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    /**
     * Save a recipe to the database.
     * @param recipeDTO The Recipe object to save in the database.
     * @return A String stating whether the method was successful or not.
     */
    @PostMapping("/recipes")
    public String saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.saveRecipe(recipeDTO);
    }

    /**
     * Delete a recipe from the database.
     * @param id Unique identifier of the recipe to delete.
     * @return A String stating whether the method was successful or not.
     */
    @DeleteMapping("/recipes/{id}")
    public String deleteRecipeById(@PathVariable int id) {
        return recipeService.deleteById(id);
    }
    // ----------------------------------------------------------------------------------------------------

    // ---------- API Calls for Users. ----------

    /**
     * Get all users from the User database.
     * @return a list of users.
     */
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Get a specified user object
     * @param id Unique identifier for a specific user.
     * @return The user object corresponding to the unique identifier provided.
     */
    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    /**
     * Save a user to the database.
     * @param userDTO The user object to save in the database.
     * @return A String stating whether the method was successful or not.
     */
    @PostMapping("/users")
    public String saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    /**
     *
     * @param id Unique identifier for the specific user.
     * @return A String stating whether the method was successful or not.
     */
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

}
