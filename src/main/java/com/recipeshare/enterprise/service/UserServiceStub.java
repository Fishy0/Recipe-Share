package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.UserDAOStub;
import com.recipeshare.enterprise.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceStub implements IUserService {

    private final UserDAOStub userDAOStub;

    public UserServiceStub(UserDAOStub userDAOStub) {
        this.userDAOStub = userDAOStub;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAOStub.getAllUsers();
    }

    @Override
    public UserDTO fetchById(int id){
        return userDAOStub.fetchById(1);
    }
    @Override
    public void deleteById(int id) {
        userDAOStub.deleteById(1);
    }

    public boolean saveById(UserDTO user) {
        user = new UserDTO(1, "Bob", "bob@gmail.com", "bob123", 5, "A profile description");
        return userDAOStub.save(user);
    }

}

