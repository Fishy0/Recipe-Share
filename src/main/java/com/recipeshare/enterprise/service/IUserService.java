package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> GetAllUsers();
    UserDTO fetchById(int id);
    void deleteById(int id);
    UserDTO saveById(UserDTO user);
}
