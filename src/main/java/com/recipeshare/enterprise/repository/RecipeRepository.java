package com.recipeshare.enterprise.repository;

import com.recipeshare.enterprise.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByRecipeCategory(String recipeCategory);
    List<Recipe> findAllByOrderByRecipeLikesDesc();

    @Modifying
    @Transactional
    @Query("UPDATE Recipe r SET r.recipeLikes = r.recipeLikes + 1 WHERE r.recipeId = :id")
    void incrementLikes(@Param("id") int id);
}
