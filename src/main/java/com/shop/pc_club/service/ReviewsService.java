package com.shop.pc_club.service;

import com.shop.pc_club.model.Reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> findAllReviews();
    Reviews findReviewById(Long id);
    Reviews addReview(Reviews review);
    void deleteReview(Long id);
}
