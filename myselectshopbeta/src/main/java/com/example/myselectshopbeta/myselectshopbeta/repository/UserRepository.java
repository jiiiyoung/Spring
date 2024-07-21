package com.example.myselectshopbeta.myselectshopbeta.repository;

import com.example.myselectshopbeta.myselectshopbeta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
