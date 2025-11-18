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

    public UserDTO getUserById(int id) {
        Optional<Users> users = repo.findById(id);
        return users.map(this::convertToDTO).orElse(null);
    }

    public void saveUser(UserDTO user) {
        Users userEntity = convertToEntity(user);
        repo.save(userEntity);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public boolean validateUser(String userName, String userPassword) {
        return repo.findByUserNameAndUserPassword(userName, userPassword).isPresent();
    }

    public Optional<Users> findByUserName(String userName) {
        return repo.findByUserName(userName);
    }

    // Conversion methods
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

    private Users convertToEntity(UserDTO dto) {
        Users user = new Users();
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());
        return user;
    }
}
