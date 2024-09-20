package com.shop.pc_club.service;

import com.shop.pc_club.model.Payments;
import com.shop.pc_club.model.ModelUser;
import com.shop.pc_club.repository.PaymentsRepository;
import com.shop.pc_club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryPaymentsServiceImpl implements PaymentsService {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Payments> findAllPayments() {
        List<Payments> paymentsList = new ArrayList<>();
        paymentsRepository.findAll().forEach(paymentsList::add);
        return paymentsList;
    }

    @Override
    public Payments findPaymentById(Long id) {
        return paymentsRepository.findById(id).orElse(null);
    }

    @Override
    public Payments addPayment(Payments payment) {
        return paymentsRepository.save(payment);
    }

    @Override
    public Payments updatePayment(Payments payment) {
        return paymentsRepository.save(payment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentsRepository.deleteById(id);
    }


    @Override
    public void makePayment(Long userId, Double amount) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Payments payment = new Payments();
        payment.setUser(user);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(amount);

        paymentsRepository.save(payment);
    }
}
