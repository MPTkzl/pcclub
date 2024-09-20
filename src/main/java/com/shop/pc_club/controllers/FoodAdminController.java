package com.shop.pc_club.controllers;

import com.shop.pc_club.model.Food;
import com.shop.pc_club.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/food")
public class FoodAdminController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAddFoodForm(Model model) {
        model.addAttribute("food", new Food());
        return "addFood";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addFood(@ModelAttribute Food food) {
        foodService.addFood(food);
        return "redirect:/admin/food/add?success";
    }
}
