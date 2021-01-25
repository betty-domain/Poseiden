package com.poseiden.springboot.controllers;

import com.poseiden.springboot.domain.Rating;
import com.poseiden.springboot.services.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller in charge of any request related to Rating entity
 */
@Controller
public class RatingController {
    @Autowired
    IRatingService ratingService;

    /**
     * Return Rating List page content
     *
     * @param model list of Rating
     * @return Rating List page
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratingList",ratingService.findAll());
        return "rating/list";
    }

    /**
     * Return the add rating page
     *
     * @param rating rating entity to add
     * @return add rating page
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Create a rating if valid
     *
     * @param rating    rating to create
     * @param result contains errors if rating is not valid
     * @param model  list of rating
     * @return list of rating page if added rating is valid, stay at rating/add page otherwise
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Rating list
        if (result.hasErrors()) {
            return "rating/add";
        }
        ratingService.save(rating);
        model.addAttribute("ratingList",ratingService.findAll());
        return "redirect:/rating/list";
    }

    /**
     * Return filled update rating page for a specific rating
     *
     * @param id    id of rating to update
     * @param model rating to Update
     * @return update rating page, can throw IllegalArgumentException if id of rating is invalid
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Rating by Id and to model then show to the form
        Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);

        return "rating/update";
    }

    /**
     * Update a rating if valid
     *
     * @param id      if of rating to update
     * @param rating rating with modified values
     * @param result  contains errors if rating is not invalid
     * @param model   list of rating
     * @return list of rating page if updated rating is valid, stay at rating/update page otherwise
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            return "rating/update";
        }
        ratingService.save(rating);
        model.addAttribute("ratingList",ratingService.findAll());
        return "redirect:/rating/list";
    }

    /**
     * Delete rating by Id
     *
     * @param id    id of rating to delete
     * @param model List of rating if id is valid
     * @return rating's List page if id is valid, throw IllegalArgumentException otherwise
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Rating by Id and delete the Rating, return to Rating list
        Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingService.delete(rating);
        model.addAttribute("ratingList",ratingService.findAll());
        return "redirect:/rating/list";
    }
}
