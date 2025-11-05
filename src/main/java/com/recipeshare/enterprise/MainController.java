package com.recipeshare.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "Home";
    }

    @RequestMapping("/results")
    public String results() {
        return "SearchResultsPage";
    }

}
