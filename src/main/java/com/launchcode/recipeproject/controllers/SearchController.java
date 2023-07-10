package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


//Copied everything over from ass3techjobs
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
//        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping (value = "results")
    public String displaySearchResults(Model model , @RequestParam String searchType, @RequestParam String searchTerm) {
//        ArrayList<Job> jobs;
//        if (searchTerm.equals("all") || searchTerm.equals("")){
//            jobs = JobData.findAll();
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//        }
//        model.addAttribute("columns", columnChoices);
//        model.addAttribute("jobs", jobs);

        return "search";
    }

}