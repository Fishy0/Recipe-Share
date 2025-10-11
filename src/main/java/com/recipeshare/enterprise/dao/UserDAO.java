package com.recipeshare.enterprise.dao;

import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.entity.Users;
import com.recipeshare.enterprise.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserDAO implements IUserDAO {

    private final UserRepository repo;

    public UserDAO(UserRepository repo) { this.repo = repo; }

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> users = repo.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO fetchById(int id) {
        Optional<Users> users = repo.findById(id);
        return users.map(this::convertToDTO).orElse(null);
    }

    public boolean save(UserDTO user) {
        return true;
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

    // DTO converter \\
    private UserDTO convertToDTO(Users user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                user.getNumberOfRecipes(),
                user.getProfileDescription()
        );
    }
}
