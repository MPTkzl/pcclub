package com.shop.pc_club.service;

import com.shop.pc_club.model.GeneralHall;
import java.util.List;

public interface    GeneralHallService {
    List<GeneralHall> findAll();
    GeneralHall findById(Long id);
    GeneralHall addComputer(GeneralHall generalHall);
    GeneralHall updateComputer(GeneralHall generalHall);
    void deleteComputer(Long id);
}