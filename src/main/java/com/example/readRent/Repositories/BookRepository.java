package com.example.readRent.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.readRent.Entities.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

} 
