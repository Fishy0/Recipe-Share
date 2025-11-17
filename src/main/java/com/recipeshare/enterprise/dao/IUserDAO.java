package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.entity.Users;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    void deleteById(int id);
    void saveUser(UserDTO user);

    boolean validateUser(String userName, String userPassword);

    Optional<Users> findByUserName(String userName);
}
