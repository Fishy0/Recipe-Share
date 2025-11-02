package com.recipeshare.enterprise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    int userId;

    @NotBlank
    String userName;

    @Email
    String userEmail;

    @NotBlank
    String userPassword;

    @Min(0)
    int numberOfRecipes;
    String profileDescription;
}
