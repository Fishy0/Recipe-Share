package com.recipeshare.enterprise.repository;

import com.recipeshare.enterprise.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByRecipeCategory(String recipeCategory);
}
