package com.recipeshare.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/UserIndex")
    public String userIndex() {
        return "UserIndex";
    }

    @GetMapping("/Category")
    public String category() {
        return "Category";
    }
}
