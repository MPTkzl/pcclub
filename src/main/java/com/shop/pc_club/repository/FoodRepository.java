package com.shop.pc_club.repository;

import com.shop.pc_club.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long> {
}
