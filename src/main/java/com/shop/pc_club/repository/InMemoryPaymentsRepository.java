package com.shop.pc_club.repository;

import com.shop.pc_club.model.Payments;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPaymentsRepository {

    private final List<Payments> paymentsList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Payments> findAll() {
        return new ArrayList<>(paymentsList);
    }

    public Optional<Payments> findById(Long id) {
        return paymentsList.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst();
    }

    public Payments save(Payments payment) {
        if (payment.getId() == null) {
            payment.setId(idCounter.getAndIncrement());
            paymentsList.add(payment);
        } else {
            paymentsList.removeIf(existingPayment -> existingPayment.getId().equals(payment.getId()));
            paymentsList.add(payment);
        }
        return payment;
    }

    public void deleteById(Long id) {
        paymentsList.removeIf(payment -> payment.getId().equals(id));
    }
}
