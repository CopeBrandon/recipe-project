package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.TagRepository;
import com.launchcode.recipeproject.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("recipe/tags")
public class TagsController {

    //New Tags can be added @ localhost8080/recipe/tags

    @Autowired
    TagRepository tagRepository;

    @GetMapping
    public String displayCreateTagForm(Model model){
        model.addAttribute("title", "Create Tag");
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute(new Tag());
        return "recipe/tags";
    }

    @PostMapping
    public String processCreateTagForm(@ModelAttribute @Valid Tag tag,
                                       Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Tags");
            return "recipe/tags";
        }

        //Makes tags uniform
        tag.setName(tag.getName().toLowerCase());
        tagRepository.save(tag);

        //Creates model attributes so the user may keep inputting more tags
        model.addAttribute("title", "Create Tags");
        model.addAttribute("tags", tagRepository.findAll());

        return "recipe/tags";
    }
}
