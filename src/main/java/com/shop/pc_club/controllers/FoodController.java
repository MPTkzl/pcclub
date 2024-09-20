package com.shop.pc_club.controllers;

import com.shop.pc_club.model.Food;
import com.shop.pc_club.model.ModelUser;
import com.shop.pc_club.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public String listFood(Model model) {
        model.addAttribute("foodList", foodService.findAllFood());
        return "foodList";
    }

    @PostMapping("/buy")
    public String buyFood(@RequestParam Long foodId, @RequestParam String username) {
        foodService.buyFood(foodId, username);
        return "redirect:/food";
    }
}
