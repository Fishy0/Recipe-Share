package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.entity.Users;
import com.recipeshare.enterprise.service.IRecipeService;
import com.recipeshare.enterprise.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
    This controller provides methods for accessing and modifying User data.
 */

@Controller
public class UserController {

    private final IUserService userService;
    private final IRecipeService recipeService;

    public UserController(IUserService userService, IRecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @RequestMapping("/UserIndex")
    public String index() {
        return "UserIndex";
    }


    /**
     * Get all users from the User database.
     * @return a list of users.
     */
    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Get a specified user object
     * @param id Unique identifier for a specific user.
     * @return The user object corresponding to the unique identifier provided.
     */
    @GetMapping("/getUserById")
    @ResponseBody
    public UserDTO fetchById(@RequestParam int id) {
        return userService.fetchById(id);
    }

    /**
     * Save a user to the database.
     * @param userDTO The user object to save in the database.
     * @return A String stating whether the method was successful or not.
     */
    @PostMapping("/saveUser")
    @ResponseBody
    public String saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    /**
     *
     * @param id Unique identifier for the specific user.
     * @return A String stating whether the method was successful or not.
     */
    @DeleteMapping("/deleteUserById")
    @ResponseBody
    public String deleteUserById(@RequestParam int id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
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
        if (session.getAttribute("userName") == null) {
            return "redirect:/login";
        }

        List<RecipeDTO> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("userName", session.getAttribute("userName"));
        return "home";
    }

    @GetMapping("/profile/{userName}")
    public String viewProfile(@PathVariable String userName, Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login";
        }

        Optional<Users> userOpt = userService.getUserByUsername(userName);

        if (userOpt.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "error";
        }

        model.addAttribute("user", userOpt.get());
        return "profile";
    }

    @GetMapping("/createUser")
    public String createUserForm() {
        return "createUser";
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

        userService.saveUser(newUser); // reuse existing service method

        model.addAttribute("success", "Account created successfully! Please log in.");
        return "login";
    }
}
