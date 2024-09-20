package com.shop.pc_club.controllers;

import com.shop.pc_club.model.Auth;
import com.shop.pc_club.model.ModelUser;
import com.shop.pc_club.model.RoleEnum;
import com.shop.pc_club.repository.AuthRepository;
import com.shop.pc_club.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationView(Model model) {
        model.addAttribute("user", new ModelUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") ModelUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("message", "Пользователь с таким email уже существует");
            return "registration";
        }

        if (user.getName() == null || user.getName().isEmpty()) { // Изменено с "getUsername" на "getName"
            model.addAttribute("message", "Имя не может быть пустым");
            return "registration";
        }

        user.setActive(true);
        userRepository.save(user);


        Auth auth = new Auth();
        auth.setUser(user);
        auth.setEmail(user.getEmail());
        auth.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        auth.setRole(RoleEnum.USER.name());

        authRepository.save(auth);

        return "redirect:/login";
    }
}
