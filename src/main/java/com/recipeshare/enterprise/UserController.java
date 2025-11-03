package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO fetchById(@PathVariable int id) {
        return userService.fetchById(id);
    }

    @PostMapping
    public String saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }
}
