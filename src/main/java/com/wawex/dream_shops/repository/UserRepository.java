package com.wawex.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wawex.dream_shops.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
