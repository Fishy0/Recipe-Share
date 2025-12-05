package com.recipeshare.enterprise.service;

import com.recipeshare.enterprise.dao.RecipeDAO;
import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.entity.Recipe;
import com.recipeshare.enterprise.repository.SupabaseImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RecipeService implements IRecipeService {

    private final RecipeDAO recipeDAO;
    private final SupabaseImageRepository supabaseImageRepository;

    public RecipeService(RecipeDAO recipeDAO, SupabaseImageRepository supabaseImageRepository) {
        this.recipeDAO = recipeDAO;
        this.supabaseImageRepository = supabaseImageRepository;
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        return recipeDAO.getAllRecipes();
    }

    @Override
    public RecipeDTO getRecipeById(int id){
        return recipeDAO.getRecipeById(id);
    }
    @Override
    public String deleteById(int id) {
        RecipeDTO recipe = recipeDAO.getRecipeById(id);
        if (recipe != null) {
            recipeDAO.deleteById(id);
            return "Recipe with id: " + id + " deleted";
        }
        else {
            return "Recipe with id: " + id + " not found";
        }
    }

    @Override
    public String saveRecipe(RecipeDTO recipeDTO, MultipartFile recipeImage) {
        try {
            Recipe savedRecipe = recipeDAO.saveRecipe(recipeDTO);
            int recipeId = savedRecipe.getRecipeId();

            // if the user chooses to attach an image, upload it
            if (recipeImage != null && !recipeImage.isEmpty()) {
                String imageUrl =  supabaseImageRepository.uploadRecipeImage(recipeImage, recipeId);
                savedRecipe.setImageUrl(imageUrl);
                recipeDAO.updateRecipe(savedRecipe);
            }

            return "Recipe created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating recipe: " + e.getMessage();
        }
    }

    public void incrementLikes(int recipeId) {
        recipeDAO.incrementLikes(recipeId);
    }

    public List<RecipeDTO> getAllRecipesSortedByLikes() {
        return recipeDAO.getAllRecipesSortedByLikes();
    }

    public List<RecipeDTO> getRecipesByUser(String userName) {
        return recipeDAO.getRecipesByUser(userName);
    }

    public List<RecipeDTO> getRecipesByCategory(String category) {
        return recipeDAO.getRecipesByCategory(category);
    }
}

