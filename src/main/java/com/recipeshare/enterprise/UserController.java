package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.entity.Users;
import com.recipeshare.enterprise.service.IRecipeService;
import com.recipeshare.enterprise.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final IUserService userService;
    private final IRecipeService recipeService;

    public UserController(IUserService userService, IRecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/createUser")
    public String createUserForm() { return "createUser"; }

    @GetMapping("/UserIndex")
    public String index() {
        return "UserIndex";
    }


    @PostMapping("/login")
    public String loginSubmit(@RequestParam String userName,
                              @RequestParam String userPassword,
                              Model model,
                              HttpSession session) {

        // if login is successful set userName in session
        if (userService.login(userName, userPassword)) {
            session.setAttribute("userName", userName);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/profile/{userName}")
    public String viewProfile(@PathVariable String userName, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        Optional<Users> userOpt = userService.getUserByUsername(userName);

        // if the optional is empty, that user doesn't exist
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
