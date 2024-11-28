package com.example.readRent.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.readRent.Entities.Rental;

public interface RentalRepository extends JpaRepository<Rental,Long>{

    List<Rental> findByUserIdAndReturnDateIsNull(Long userId);
}
