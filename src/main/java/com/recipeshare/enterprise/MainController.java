package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.entity.Users;
import com.recipeshare.enterprise.service.IRecipeService;
import com.recipeshare.enterprise.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

/**
    This controller is used for page navigation.
 */
@Controller
public class MainController {

    private final IRecipeService recipeService;
    private final IUserService userService;

    public MainController(IRecipeService recipeService, IUserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/createUser")
    public String createUserForm() {
        return "createUser";
    }

    @GetMapping("/UserIndex")
    public String index() {
        return "UserIndex";
    }

    @GetMapping("/results")
    public String showResultsPage() {
        return "SearchResultsPage";
    }

    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String userName,
                              @RequestParam String userPassword,
                              Model model,
                              HttpSession session) {

        if (userService.login(userName, userPassword)) {
            session.setAttribute("userName", userName);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
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

    @GetMapping("/profile/{userName}")
    public String viewProfile(@PathVariable String userName, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        Optional<Users> userOpt = userService.getUserByUsername(userName);

        if (userOpt.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "error";
        }
        model.addAttribute("user", userOpt.get());

        List<RecipeDTO> recipes = recipeService.getRecipesByUser(userName);
        model.addAttribute("recipes", recipes);

        int likes = calculateTotalLikes(recipes);
        model.addAttribute("likes", likes);

        return "profile";
    }

    @PostMapping("/createUser")
    public String DoCreateUser(@RequestParam String userName,
                               @RequestParam String userEmail,
                               @RequestParam String userPassword,
                               Model model) {

        UserDTO newUser = new UserDTO();
        newUser.setUserName(userName);
        newUser.setUserEmail(userEmail);
        newUser.setUserPassword(userPassword);

        userService.saveUser(newUser);

        model.addAttribute("success", "Account created successfully! Please log in.");
        return "login";
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

    @GetMapping("/createRecipe")
    public String createRecipeForm(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "createRecipe";
    }

    @PostMapping("/createRecipe")
    public String createRecipe(@RequestParam String recipeName,
                               @RequestParam String recipeDescription,
                               @RequestParam String recipeIngredients,
                               @RequestParam String recipeCategory,
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

        String result = recipeService.saveRecipe(recipeDTO);

        if (result.contains("successfully")) {
            return "redirect:/home";
        }

        model.addAttribute("error", result);
        return "createRecipe";
    }

    @PostMapping("/recipe/like/{id}")
    public String likeRecipe(@PathVariable int id) {

        recipeService.incrementLikes(id);

        return "redirect:/recipe/" + id;
    }


    // helper functions
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("userName") != null;
    }
    public int calculateTotalLikes(List<RecipeDTO> recipes) {
        int likes = 0;

        for (RecipeDTO recipe : recipes) {
            likes += recipe.getRecipeLikes();
        }

        return likes;
    }
}
