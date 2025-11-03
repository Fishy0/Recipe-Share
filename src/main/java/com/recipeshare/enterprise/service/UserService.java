package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.UserDAO;
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
    public boolean deleteUserById(int id) {
        UserDTO user = userDAO.fetchById(id);
        if (user == null) {
            return false;
        }
        userDAO.deleteById(id);
        return true;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("User data must not be null");
        }
        return userDAO.saveUser(userDTO);
    }

}
