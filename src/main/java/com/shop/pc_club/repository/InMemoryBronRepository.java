package com.shop.pc_club.repository;

import com.shop.pc_club.model.Bron;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryBronRepository {

    private final List<Bron> bronList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Bron> findAll() {
        return new ArrayList<>(bronList);
    }

    public Optional<Bron> findById(Long id) {
        return bronList.stream()
                .filter(bron -> bron.getId().equals(id))
                .findFirst();
    }

    public Bron save(Bron bron) {
        if (bron.getId() == null) {
            bron.setId(idCounter.getAndIncrement());
            bronList.add(bron);
        } else {
            bronList.removeIf(existingBron -> existingBron.getId().equals(bron.getId()));
            bronList.add(bron);
        }
        return bron;
    }

    public void deleteById(Long id) {
        bronList.removeIf(bron -> bron.getId().equals(id));
    }
}
