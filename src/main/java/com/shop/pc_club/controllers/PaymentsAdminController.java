package com.shop.pc_club.controllers;

import com.shop.pc_club.model.Payments;
import com.shop.pc_club.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/payments")
public class PaymentsAdminController {

    @Autowired
    private PaymentsService paymentsService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String viewAllPayments(Model model) {
        List<Payments> paymentsList = paymentsService.findAllPayments();
        model.addAttribute("payments", paymentsList);
        return "payments";
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePayment(@PathVariable Long id) {
        paymentsService.deletePayment(id);
        return "redirect:/admin/payments";
    }
}
