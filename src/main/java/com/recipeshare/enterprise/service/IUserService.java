package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.entity.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO fetchById(int id);
    String deleteUserById(int id);
    String saveUser(UserDTO user);
    boolean login(String userName, String userPassword);
    Optional<Users> getUserByUsername(String userName);
}
