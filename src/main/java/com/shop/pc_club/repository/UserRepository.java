package com.shop.pc_club.repository;

import com.shop.pc_club.model.ModelUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ModelUser, Long> {
    Optional<ModelUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
