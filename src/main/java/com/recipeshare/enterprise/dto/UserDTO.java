package com.recipeshare.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    int userId;
    String userName;
    String userEmail;
    String userPassword;
    int numberOfRecipes;
    String profileDescription;
}
