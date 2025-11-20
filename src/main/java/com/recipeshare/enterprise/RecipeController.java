package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class RecipeController {

    private final IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String showHomePage(HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/results")
    public String showResultsPage() {
        return "SearchResultsPage";
    }


    @PostMapping("/recipe/like/{id}")
    public String likeRecipe(@PathVariable int id) {

        recipeService.incrementLikes(id);

        return "redirect:/recipe/" + id;
    }

    @PostMapping("/createRecipe")
    public String createRecipe(@RequestParam String recipeName,
                               @RequestParam String recipeDescription,
                               @RequestParam String recipeIngredients,
                               @RequestParam String recipeCategory,
                               @RequestParam(required = false) MultipartFile recipeImage,
                               HttpSession session,
                               Model model) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeName(recipeName);
        recipeDTO.setRecipeDescription(recipeDescription);
        recipeDTO.setRecipeIngredients(recipeIngredients);
        recipeDTO.setRecipeCategory(recipeCategory);
        recipeDTO.setRecipeLikes(0);

        String userName = (String) session.getAttribute("userName");
        recipeDTO.setRecipeCreatedBy(userName);

        String result = recipeService.saveRecipe(recipeDTO, recipeImage);

        if (result.contains("successfully")) {
            return "redirect:/home";
        }

        model.addAttribute("error", result);
        return "createRecipe";
    }

    @GetMapping("/createRecipe")
    public String createRecipeForm(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "createRecipe";
    }

    @GetMapping("/recipe/{id}")
    public String viewRecipe(@PathVariable("id") int id, Model model) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipeDetail";
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

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        List<RecipeDTO> recipes = recipeService.getAllRecipesSortedByLikes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("userName", session.getAttribute("userName"));
        return "home";
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("userName") != null;
    }

}
