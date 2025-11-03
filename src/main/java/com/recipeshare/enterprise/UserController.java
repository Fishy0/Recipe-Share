package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUserById")
    @ResponseBody
    public ResponseEntity<UserDTO> fetchById(@RequestParam int id) {
        UserDTO user = userService.fetchById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO savedUser = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteUserById")
    @ResponseBody
    public ResponseEntity<Void> deleteUserById(@RequestParam int id) {
        boolean deleted = userService.deleteUserById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
