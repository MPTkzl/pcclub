package com.shop.pc_club.service;

import com.shop.pc_club.model.Reviews;
import com.shop.pc_club.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public List<Reviews> findAllReviews() {
        return reviewsRepository.findAll();
    }

    @Override
    public Reviews findReviewById(Long id) {
        return reviewsRepository.findById(id).orElse(null);
    }

    @Override
    public Reviews addReview(Reviews review) {
        return reviewsRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewsRepository.deleteById(id);
    }
}
