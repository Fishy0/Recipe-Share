package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.UserDTO;
import java.util.List;

public interface IUserDAO {
    List<UserDTO> getAllUsers();
    UserDTO fetchById(int id);
    void deleteById(int id);
    UserDTO saveUser(UserDTO user);
}
