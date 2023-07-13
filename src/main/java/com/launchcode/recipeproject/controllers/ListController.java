package org.launchcode.techjobs.mvc.controllers;


import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//Copied everything over from ass3techjobs
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private IngredientRepository IngredientRepository;
    @Autowired
    private RecipeRepository RecipeRepository;
    @Autowired
    private UserRepository UserRepository;


    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("recipe", "Recipe");
        columnChoices.put("tags", "Tags");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute();
        model.addAttribute();

        return "list";
    }

    @GetMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Recipe> recipes;
        if (column.toLowerCase().equals("all")){
//            jobs = jobRepository.findAll();
//            model.addAttribute("title", "All Jobs");
        } else {
//            jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());
//            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("recipes", recipes);

        return "list-jobs";
    }
}