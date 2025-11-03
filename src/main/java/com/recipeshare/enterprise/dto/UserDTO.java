package com.recipeshare.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private int numberOfRecipes;
    private String profileDescription;
}
