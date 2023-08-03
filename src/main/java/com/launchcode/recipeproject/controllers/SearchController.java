package com.launchcode.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SearchController {

    @GetMapping("search")
    public String showMySearchPage() {
        return "index";

    }

}
