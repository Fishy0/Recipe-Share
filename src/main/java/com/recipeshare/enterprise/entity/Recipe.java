package com.recipeshare.enterprise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recipe {

    protected Recipe() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private String recipeIngredients;
    private short recipeRating;
}
