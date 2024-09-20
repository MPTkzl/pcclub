package com.shop.pc_club.controllers;

import com.shop.pc_club.model.Reviews;
import com.shop.pc_club.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;


    @GetMapping("/reviews")
    public String showReviewForm(Model model) {
        model.addAttribute("reviews", new Reviews());
        return "addReview";
    }


    @PostMapping("/reviews")
    public String submitReview(@ModelAttribute Reviews reviews) {
        reviewsService.addReview(reviews);
        return "redirect:/reviews";
    }


    @GetMapping("/admin/reviews/all")
    public String listAllReviews(Model model) {
        model.addAttribute("reviewsList", reviewsService.findAllReviews());
        return "allReviews";
    }


    @GetMapping("/reviews/{id}")
    public String getReviewById(@PathVariable Long id, Model model) {
        Reviews review = reviewsService.findReviewById(id);
        if (review != null) {
            model.addAttribute("review", review);
            return "reviewDetails";
        }
        return "error";
    }


    @GetMapping("/admin/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewsService.deleteReview(id);
        return "redirect:/admin/reviews/all";
    }
}
