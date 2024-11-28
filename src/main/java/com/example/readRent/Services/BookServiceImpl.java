package com.example.readRent.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.readRent.Entities.Book;
import com.example.readRent.Repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {

    private final BookRepository bookRepository;

    /**
     * Create a new book.
     *
     * @param book Book entity to be created
     * @return Created book entity
     */
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Get all available books.
     *
     * @return List of available books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Get a book by ID.
     *
     * @param id Book ID
     * @return Book entity
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    /**
     * Update an existing book.
     *
     * @param id   Book ID
     * @param book Book entity with updated details
     * @return Updated book entity
     */
    public Book updateBook(Long id, Book book) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setGenre(book.getGenre());
        existingBook.setAvailabilityStatus(book.isAvailabilityStatus());
        return bookRepository.save(existingBook);
    }

    /**
     * Delete a book.
     *
     * @param id Book ID
     */
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}