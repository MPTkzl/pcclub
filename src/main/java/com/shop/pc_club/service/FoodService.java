package com.shop.pc_club.service;

import com.shop.pc_club.model.Food;

import java.util.List;

public interface FoodService {
    List<Food> findAllFood();
    Food findFoodById(Long id);
    void addFood(Food food);
    void buyFood(Long foodId, String username);
}
