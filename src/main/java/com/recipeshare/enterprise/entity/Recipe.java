package com.recipeshare.enterprise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Recipe {

    protected Recipe() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private String recipeIngredients;
    private String recipeCategory;
    private short recipeRating;
}
