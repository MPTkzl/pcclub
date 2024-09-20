package com.shop.pc_club.controllers;

import com.shop.pc_club.model.Bron;
import com.shop.pc_club.model.GeneralHall;
import com.shop.pc_club.service.BronService;
import com.shop.pc_club.service.GeneralHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bron")
public class BronController {

    @Autowired
    private BronService bronService;

    @Autowired
    private GeneralHallService generalHallService;


    @GetMapping
    public String showBronPage(Model model) {
        List<Bron> bronList = bronService.findAllBron();
        model.addAttribute("bronList", bronList);

        List<GeneralHall> computers = generalHallService.findAll();
        model.addAttribute("computers", computers);

        model.addAttribute("bron", new Bron());

        return "bronList";
    }


    @PostMapping("/add")
    public String addBron(@ModelAttribute Bron bron, @RequestParam Long computerId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        bronService.bronComputer(computerId, username, bron.getBronStart(), bron.getBronEnd());
        return "redirect:/bron";
    }


    @PostMapping("/update")
    public String updateBron(@ModelAttribute Bron bron) {
        bronService.updateBron(bron);
        return "redirect:/bron";
    }


    @PostMapping("/delete")
    public String deleteBron(@RequestParam Long id) {
        bronService.deleteBron(id);
        return "redirect:/bron";
    }
}
