package com.recipeshare.enterprise.repository;

import com.recipeshare.enterprise.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {}
