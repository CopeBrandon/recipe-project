package com.launchcode.recipeproject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import static com.launchcode.recipeproject.controllers.ListController.columnChoices;


//Copied everything over from ass4techjobs
@Controller
@RequestMapping
public class SearchController {

//    @Autowired
//    private RecipeRepository RecipeRepository;

    @RequestMapping ("search")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping ("search/results")
    public String displaySearchResults(Model model , @RequestParam String searchType, @RequestParam String searchTerm) {



//        Iterable<Job> jobs;
//        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
//            jobs = jobRepository.findAll();
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
//        }
        model.addAttribute("columns", columnChoices);
//        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
//        model.addAttribute("jobs", jobs);

        return "search";
    }

    @RequestMapping ("adv-search")
    public String advancedSearch(Model model) {
        model.addAttribute("columns", columnChoices);
        return "adv-search";
    }

    @PostMapping ("adv-search/results")
    public String displayAdvancedSearchResults(Model model , @RequestParam String searchType, @RequestParam String searchTerm) {



//        Iterable<Job> jobs;
//        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
//            jobs = jobRepository.findAll();
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
//        }
        model.addAttribute("columns", columnChoices);
//        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
//        model.addAttribute("jobs", jobs);

        return "adv-search";
    }

}