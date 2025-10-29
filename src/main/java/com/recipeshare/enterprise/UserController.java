package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import com.recipeshare.enterprise.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById")
    @ResponseBody
    public UserDTO fetchById(@RequestParam int id) {
        return userService.fetchById(id);
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public String saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/deleteUserById")
    @ResponseBody
    public String deleteUserById(@RequestParam int id) {
        return userService.deleteUserById(id);
    }
}
