package com.shop.pc_club.repository;

import com.shop.pc_club.model.Food;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryFoodRepository {

    private final List<Food> foodList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Food> findAll() {
        return new ArrayList<>(foodList);
    }

    public Optional<Food> findById(Long id) {
        return foodList.stream()
                .filter(food -> food.getId().equals(id))
                .findFirst();
    }

    public Food save(Food food) {
        if (food.getId() == null) {
            food.setId(idCounter.getAndIncrement());
            foodList.add(food);
        } else {
            foodList.removeIf(existingFood -> existingFood.getId().equals(food.getId()));
            foodList.add(food);
        }
        return food;
    }

    public void deleteById(Long id) {
        foodList.removeIf(food -> food.getId().equals(id));
    }
}
