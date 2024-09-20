package com.shop.pc_club.controllers;

import com.shop.pc_club.model.ModelUser;
import com.shop.pc_club.model.RoleEnum; // Убедитесь, что это правильный импорт
import com.shop.pc_club.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        model.addAttribute("user_list", userRepository.findAll());
        return "index";
    }

    @GetMapping("/admin/users/{id}")
    public String userDetails(@PathVariable("id") Long id, Model model) {
        ModelUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user_object", user);
        return "info";
    }

    @GetMapping("/admin/users/{id}/update")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        ModelUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user_object", user);
        model.addAttribute("roles", RoleEnum.values());
        return "update";
    }

    @PostMapping("/admin/users/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @Valid @ModelAttribute("user_object") ModelUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", RoleEnum.values());
            return "update";
        }

        ModelUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setActive(user.isActive());
        existingUser.setRoles(user.getRoles());

        userRepository.save(existingUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new ModelUser());
        return "registration";
    }

    @PostMapping("/admin/users")
    public String handleUser(@Valid @ModelAttribute("user") ModelUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        userRepository.save(user);
        return "redirect:/admin/users";
    }
}
