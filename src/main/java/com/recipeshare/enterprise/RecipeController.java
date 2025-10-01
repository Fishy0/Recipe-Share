package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dto.RecipeDTO;
import com.recipeshare.enterprise.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RecipeController {

    private IRecipeService RecipeServiceStub;

    @RequestMapping("RecipeIndex")
    public String index() {
            return "RecipeIndex";
    }


    @GetMapping("/recipe")
    @ResponseBody
    public List<RecipeDTO> getAllRecipes() {
        return RecipeServiceStub.getAllRecipes();
    }

}
