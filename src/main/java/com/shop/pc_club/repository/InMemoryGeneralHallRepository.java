package com.shop.pc_club.repository;

import com.shop.pc_club.model.GeneralHall;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryGeneralHallRepository {

    private final List<GeneralHall> generalHalls = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<GeneralHall> findAll() {
        return new ArrayList<>(generalHalls);
    }

    public GeneralHall save(GeneralHall generalHall) {
        if (generalHall.getId() == null) {
            generalHall.setId(idCounter.getAndIncrement());
            generalHalls.add(generalHall);
        } else {
            generalHalls.removeIf(existingHall -> existingHall.getId().equals(generalHall.getId()));
            generalHalls.add(generalHall);
        }
        return generalHall;
    }

    public Optional<GeneralHall> findById(Long id) {
        return generalHalls.stream()
                .filter(generalHall -> generalHall.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id) {
        generalHalls.removeIf(generalHall -> generalHall.getId().equals(id));
    }


}
