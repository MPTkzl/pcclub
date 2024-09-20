package com.shop.pc_club.repository;

import com.shop.pc_club.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByEmail(String email);
    boolean existsByEmail(String email);
}
