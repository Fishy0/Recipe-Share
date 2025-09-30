package com.recipeshare.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    @RequestMapping("RecipeIndex")
    public String index() {
            return "RecipeIndex";
    }
}
