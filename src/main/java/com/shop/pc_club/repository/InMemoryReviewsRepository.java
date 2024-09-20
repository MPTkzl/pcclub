package com.shop.pc_club.repository;

import com.shop.pc_club.model.Reviews;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryReviewsRepository {

    private final List<Reviews> reviewsList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);


    public List<Reviews> findAll() {
        return new ArrayList<>(reviewsList);
    }


    public Optional<Reviews> findById(Long id) {
        return reviewsList.stream()
                .filter(review -> review.getId().equals(id))
                .findFirst();
    }


    public Reviews save(Reviews review) {
        if (review.getId() == null) {
            review.setId(idCounter.getAndIncrement());
            reviewsList.add(review);
        } else {
            reviewsList.removeIf(existingReview -> existingReview.getId().equals(review.getId()));
            reviewsList.add(review);
        }
        return review;
    }


    public void deleteById(Long id) {
        reviewsList.removeIf(review -> review.getId().equals(id));
    }
}
