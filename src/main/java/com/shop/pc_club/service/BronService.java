package com.shop.pc_club.service;

import com.shop.pc_club.model.Bron;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BronService {
    List<Bron> findAllBron();
    Bron findBronById(Long id);
    Bron addBron(Bron bron);
    Bron updateBron(Bron bron);
    void deleteBron(Long id);
    void bronComputer(Long computerId, String email, LocalDateTime bronStart, LocalDateTime bronEnd);
}
