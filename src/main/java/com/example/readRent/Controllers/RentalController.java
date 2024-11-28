package com.example.readRent.Controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.readRent.Entities.Rental;
import com.example.readRent.Services.RentalServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalServiceImpl rentalService;

    /**
     * Rent a book.
     *
     * @param userId User ID
     * @param bookId Book ID
     * @return Rental entity
     */
    @PostMapping("/rent/{userId}/{bookId}")
    public ResponseEntity<Rental> rentBook(@PathVariable Long userId, @PathVariable Long bookId) {
        Rental rental = rentalService.rentBook(userId, bookId);
        return ResponseEntity.ok(rental);
    }

    /**
     * Return a rented book.
     *
     * @param rentalId Rental ID
     * @return Updated rental entity
     */
    @PostMapping("/return/{rentalId}")
    public ResponseEntity<Rental> returnBook(@PathVariable Long rentalId) {
        Rental rental = rentalService.returnBook(rentalId);
        return ResponseEntity.ok(rental);
    }

    /**
     * Get all rentals for a user.
     *
     * @param userId User ID
     * @return List of rentals
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<Rental>> getUserRentals(@PathVariable Long userId) {
        Optional<Rental> rentals = rentalService.getUserRentals(userId);
        return ResponseEntity.ok(rentals);
    }
}
