package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.UserDAO;
import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public UserDTO fetchById(int id){
        return userDAO.fetchById(id);
    }
    @Override
    public String deleteUserById(int id) {
        UserDTO user = userDAO.fetchById(id);
        if (user != null) {
            userDAO.deleteById(id);
            return "User with id: " + id + " deleted";
        }
        else {
            return "User with id: " + id + " not found";
        }
    }

    public String saveUser(UserDTO userDTO) {
        if (userDTO == null) {
            return "ERROR: Invalid user data.";
        }
        else {
            userDAO.saveUser(userDTO);
            return "User saved successfully";
        }
    }

}