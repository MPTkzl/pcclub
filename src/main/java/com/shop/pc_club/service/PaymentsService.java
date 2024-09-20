package com.shop.pc_club.service;

import com.shop.pc_club.model.Payments;

import java.util.List;

public interface PaymentsService {
    List<Payments> findAllPayments();
    Payments findPaymentById(Long id);
    Payments addPayment(Payments payment);
    Payments updatePayment(Payments payment);
    void deletePayment(Long id);
    void makePayment(Long userId, Double amount);
}
