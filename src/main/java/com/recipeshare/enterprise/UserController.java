package com.recipeshare.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("UserIndex")
    public String index() {
        return "UserIndex";
    }
}
