package com.shop.pc_club.service;

import com.shop.pc_club.model.Bron;
import com.shop.pc_club.model.GeneralHall;
import com.shop.pc_club.model.ModelUser;
import com.shop.pc_club.repository.BronRepository;
import com.shop.pc_club.repository.GeneralHallRepository;
import com.shop.pc_club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryBronServiceImpl implements BronService {

    @Autowired
    private BronRepository bronRepository;

    @Autowired
    private GeneralHallRepository generalHallRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Bron> findAllBron() {
        List<Bron> bronList = new ArrayList<>();
        bronRepository.findAll().forEach(bronList::add);
        return bronList;
    }

    @Override
    public Bron findBronById(Long id) {
        return bronRepository.findById(id).orElse(null);
    }

    @Override
    public Bron addBron(Bron bron) {
        return bronRepository.save(bron);
    }

    @Override
    public Bron updateBron(Bron bron) {
        return bronRepository.save(bron);
    }

    @Override
    public void deleteBron(Long id) {
        bronRepository.deleteById(id);
    }

    @Override
    public void bronComputer(Long computerId, String email, LocalDateTime bronStart, LocalDateTime bronEnd) {
        GeneralHall generalHall = generalHallRepository.findById(computerId)
                .orElseThrow(() -> new RuntimeException("Computer not found"));

        ModelUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bron bron = new Bron();
        bron.setGeneralHall(generalHall);
        bron.setUser(user);
        bron.setBronStart(bronStart);
        bron.setBronEnd(bronEnd);


        Duration duration = Duration.between(bronStart, bronEnd);
        long hours = duration.toHours();
        double totalCost = generalHall.getPricePerHour() * hours;
        bron.setTotalCost(totalCost);

        bronRepository.save(bron);
    }
}
