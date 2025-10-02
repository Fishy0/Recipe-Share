package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOStub implements IUserDAO {

    @Override
    public List<UserDTO> getAllUsers() {

        return new ArrayList<>(List.of(
                new UserDTO(1, "Bob", "bob@gmail.com", "bob123", 5, "A profile description"),
                new UserDTO(2, "Larry", "larry@gmail.com", "password1", 3, "A profile description"),
                new UserDTO(3, "Steve", "steve@gmail.com", "123123", 2, "A profile description")
        ));
    }

    public UserDTO fetchById(int id) {
        return new UserDTO(1, "Bob", "bob@gmail.com", "bob123", 5, "A profile description");
    }

    public void deleteById(int id) {};

    public boolean save(UserDTO user) {
        return true;
    }
}

