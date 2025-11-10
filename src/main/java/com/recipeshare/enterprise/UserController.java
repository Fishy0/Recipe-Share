package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
    This controller provides methods for accessing and modifying User data.
 */

@Controller
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
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
}
