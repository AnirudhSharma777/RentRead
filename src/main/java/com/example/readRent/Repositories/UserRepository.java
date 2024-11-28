package com.example.readRent.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.readRent.Entities.Role;
import com.example.readRent.Entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
