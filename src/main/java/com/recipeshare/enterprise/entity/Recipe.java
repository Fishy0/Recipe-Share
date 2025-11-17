package com.recipeshare.enterprise.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Recipe {

    public Recipe() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private String recipeCreatedBy;
    private String recipeName;
    private String recipeDescription;
    private String recipeIngredients;
    private String recipeCategory;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int recipeLikes = 0;
}
