package com.shop.pc_club.service;

import com.shop.pc_club.model.Food;
import com.shop.pc_club.model.ModelUser;
import com.shop.pc_club.model.Payments;
import com.shop.pc_club.repository.FoodRepository;
import com.shop.pc_club.repository.UserRepository;
import com.shop.pc_club.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryFoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public List<Food> findAllFood() {
        return (List<Food>) foodRepository.findAll();
    }

    @Override
    public Food findFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public void addFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void buyFood(Long foodId, String username) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        ModelUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        food.setQuantity(food.getQuantity() - 1);
        foodRepository.save(food);


        Payments payment = new Payments();
        payment.setUser(user);
        payment.setAmount(food.getPrice());
        payment.setPaymentDate(java.time.LocalDateTime.now());
        paymentsRepository.save(payment);
    }
}
