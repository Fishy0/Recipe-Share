package com.recipeshare.enterprise.dto;

public class UserDTO {
    int userId;
    String userName;
    String userEmail;
    String userPassword;
    int numberOfRecipes;
    String profileDescription;

    public UserDTO(int userId,
                   String userName,
                   String userEmail,
                   String userPassword,
                   int numberOfRecipes,
                   String profileDescription) {

        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.numberOfRecipes = numberOfRecipes;
        this.profileDescription = profileDescription;
    }
}
