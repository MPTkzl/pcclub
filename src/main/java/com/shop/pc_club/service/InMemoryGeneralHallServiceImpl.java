package com.shop.pc_club.service;

import com.shop.pc_club.model.GeneralHall;
import com.shop.pc_club.repository.GeneralHallRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryGeneralHallServiceImpl implements GeneralHallService {

    private final GeneralHallRepository generalHallRepository;

    public InMemoryGeneralHallServiceImpl(GeneralHallRepository generalHallRepository) {
        this.generalHallRepository = generalHallRepository;
    }

    @Override
    public List<GeneralHall> findAll() {
        List<GeneralHall> generalHalls = new ArrayList<>();
        generalHallRepository.findAll().forEach(generalHalls::add);
        return generalHalls;
    }

    @Override
    public GeneralHall findById(Long id) {
        return generalHallRepository.findById(id).orElse(null);
    }

    @Override
    public GeneralHall addComputer(GeneralHall generalHall) {
        return generalHallRepository.save(generalHall);
    }

    @Override
    public GeneralHall updateComputer(GeneralHall generalHall) {
        return generalHallRepository.save(generalHall);
    }

    @Override
    public void deleteComputer(Long id) {
        generalHallRepository.deleteById(id);
    }
}
