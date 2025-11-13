package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

/**
    This controller is used for page navigation.
 */
@Controller
public class MainController {

    private final IRecipeService recipeService;

    public MainController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login";
        }

        List<RecipeDTO> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "Home";
    }

    @RequestMapping("/results")
    public String showResultsPage() {
        return "SearchResultsPage";
    }

}
