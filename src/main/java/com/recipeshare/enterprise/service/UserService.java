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
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    public boolean saveById(UserDTO user) {
        return userDAO.save(user);
    }

}