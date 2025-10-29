package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO fetchById(int id);
    String deleteUserById(int id);
    String saveUser(UserDTO user);
}
