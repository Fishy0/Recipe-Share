package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dao.RecipeDAO;
import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.entity.Recipe;
import com.recipeshare.enterprise.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class RecipeDAOTest {
    @InjectMocks
    private RecipeDAO recipeDAO;

    @Mock
    private RecipeRepository repo;

    private Recipe recipe;
    private RecipeDTO recipeDTO;

    @BeforeEach
    void setup() {
        recipe = new Recipe();
        recipe.setRecipeId(1);
        recipe.setRecipeName("Cereal");
        recipe.setRecipeCreatedBy("Bob");
        recipe.setRecipeDescription("Lucky Charms");
        recipe.setRecipeIngredients("Milk, Sugar");
        recipe.setRecipeCategory("Breakfast");
        recipe.setRecipeLikes(5);
        recipe.setImageUrl("image.png");

        recipeDTO = new RecipeDTO(
                recipe.getRecipeId(),
                recipe.getRecipeCreatedBy(),
                recipe.getRecipeName(),
                recipe.getRecipeDescription(),
                recipe.getRecipeIngredients(),
                recipe.getRecipeCategory(),
                recipe.getRecipeLikes(),
                recipe.getImageUrl()
        );
    }


    @Test
    void GivenRecipesExist_WhenGetAllRecipes_thenReturnList() {
        // Given
        given(repo.findAll()).willReturn(Arrays.asList(recipe));

        // When
        List<RecipeDTO> result = recipeDAO.getAllRecipes();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRecipeName()).isEqualTo("Cereal");
    }

    @Test
    void GivenRecipeId_WhenGetRecipeById_thenReturnRecipeDTO() {
        // Given
        given(repo.findById(1)).willReturn(Optional.of(recipe));

        // When
        RecipeDTO result = recipeDAO.getRecipeById(1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getRecipeName()).isEqualTo("Cereal");
    }

    @Test
    void GivenRecipeDTO_WhenSaveRecipe_thenReturnRecipe() {
        // Given
        given(repo.save(any(Recipe.class))).willReturn(recipe);

        // When
        Recipe saved = recipeDAO.saveRecipe(recipeDTO);

        // Then
        assertThat(saved).isNotNull();
        assertThat(saved.getRecipeName()).isEqualTo("Cereal");
    }

    @Test
    void GivenRecipeId_WhenDeleteById_thenRepoCalled() {
        // Given
        willDoNothing().given(repo).deleteById(1);

        // When
        recipeDAO.deleteById(1);

        // Then
        then(repo).should().deleteById(1);
    }

    @Test
    void GivenCategory_WhenGetRecipesByCategory_thenReturnRecipesWhereCategoryEqualsGiven() {
        // Given
        given(repo.findByRecipeCategory("Breakfast")).willReturn(Arrays.asList(recipe));

        // When
        List<RecipeDTO> results = recipeDAO.getRecipesByCategory("Breakfast");

        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getRecipeCategory()).isEqualTo("Breakfast");
    }
    @Test
    void GivenRecipeId_WhenIncrementLikes_thenIncrementLikes() {
        // Given
        willDoNothing().given(repo).incrementLikes(1);

        // When
        recipeDAO.incrementLikes(1);

        // Then
        then(repo).should().incrementLikes(1);
    }

    @Test
    void GivenRecipesExist_WhenGetAllSortedByLikes_thenReturnSortedList() {
        // Given
        given(repo.findAllByOrderByRecipeLikesDesc()).willReturn(Arrays.asList(recipe));

        // When
        List<RecipeDTO> results = recipeDAO.getAllRecipesSortedByLikes();

        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getRecipeLikes()).isEqualTo(5);
    }
    @Test
    void GivenUserName_WhenGetRecipesByUser_thenReturnUserRecipes() {
        // Given
        given(repo.findByRecipeCreatedBy("Bob")).willReturn(Arrays.asList(recipe));

        // When
        List<RecipeDTO> results = recipeDAO.getRecipesByUser("Bob");

        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getRecipeCreatedBy()).isEqualTo("Bob");
    }


}
