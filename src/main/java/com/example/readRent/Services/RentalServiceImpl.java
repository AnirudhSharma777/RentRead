package com.example.readRent.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.readRent.Entities.Book;
import com.example.readRent.Entities.Rental;
import com.example.readRent.Entities.User;
import com.example.readRent.Repositories.RentalRepository;
import com.example.readRent.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final BookServiceImpl bookService;

    /**
     * Rent a book.
     *
     * @param userId User ID
     * @param bookId Book ID
     * @return Rental entity
     */
    public Rental rentBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has already rented 2 books
        List<Rental> activeRentals = rentalRepository.findByUserIdAndReturnDateIsNull(userId);
        if (activeRentals.size() >= 2) {
            throw new RuntimeException("User cannot rent more than 2 books at a time");
        }

        Book book = bookService.getBookById(bookId);

        // Check if the book is available
        if (!book.isAvailabilityStatus()) {
            throw new RuntimeException("Book is not available for rent");
        }

        // Create a new rental record
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(java.time.LocalDate.now());

        // Set the book status to unavailable
        book.setAvailabilityStatus(false);
        bookService.updateBook(bookId, book);

        return rentalRepository.save(rental);
    }

    /**
     * Return a rented book.
     *
     * @param rentalId Rental ID
     * @return Updated rental entity
     */
    public Rental returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        // Mark the rental as returned
        rental.setRentalDate(java.time.LocalDate.now());

        // Set the book availability back to available
        Book book = rental.getBook();
        book.setAvailabilityStatus(true);
        bookService.updateBook(book.getBookId(), book);

        return rentalRepository.save(rental);
    }

    /**
     * Get all rentals for a specific user.
     *
     * @param userId User ID
     * @return List of rentals
     */
    public Optional<Rental> getUserRentals(Long userId) {
        return rentalRepository.findById(userId);
    }
}