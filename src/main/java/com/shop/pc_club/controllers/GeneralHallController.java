package com.shop.pc_club.controllers;

import com.shop.pc_club.model.GeneralHall;
import com.shop.pc_club.service.BronService;
import com.shop.pc_club.service.GeneralHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class GeneralHallController {

    @Autowired
    private GeneralHallService generalHallService;

    @Autowired
    private BronService bronService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addPc")
    public String showAddComputerPage(Model model) {
        model.addAttribute("computer", new GeneralHall());
        return "addPc";  // Название шаблона Thymeleaf для добавления компьютера
    }


    @GetMapping("/general-Hall")
    public String showGeneralHall(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<GeneralHall> computers = generalHallService.findAll();
        model.addAttribute("computers", computers);

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        return "generalHall";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addPc")
    public String addComputer(@Valid @ModelAttribute("computer") GeneralHall computer,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("computers", generalHallService.findAll());
            return "addPc";
        }
        generalHallService.addComputer(computer);
        return "redirect:/general-Hall";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update")
    public String updateComputer(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam String status,
                                 @RequestParam(required = false) String specifications,
                                 @RequestParam Double pricePerHour) {
        GeneralHall computer = new GeneralHall();
        computer.setId(id);
        computer.setName(name);
        computer.setStatus(status);
        computer.setSpecifications(specifications);
        computer.setPricePerHour(pricePerHour);

        generalHallService.updateComputer(computer);
        return "redirect:/general-Hall";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public String deleteComputer(@RequestParam Long id) {
        generalHallService.deleteComputer(id);
        return "redirect:/general-Hall";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/general-Hall")
    public String showAdminGeneralHall(Model model) {
        List<GeneralHall> computers = generalHallService.findAll();
        model.addAttribute("computers", computers);
        return "adminGeneralHall";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/general-Hall/update")
    public String adminUpdateComputer(@RequestParam Long id,
                                      @RequestParam String name,
                                      @RequestParam String status,
                                      @RequestParam(required = false) String specifications,
                                      @RequestParam Double pricePerHour) {
        GeneralHall computer = new GeneralHall();
        computer.setId(id);
        computer.setName(name);
        computer.setStatus(status);
        computer.setSpecifications(specifications);
        computer.setPricePerHour(pricePerHour);

        generalHallService.updateComputer(computer);
        return "redirect:/admin/general-Hall";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/general-Hall/delete")
    public String adminDeleteComputer(@RequestParam Long id) {
        generalHallService.deleteComputer(id);
        return "redirect:/admin/general-Hall";
    }
}
