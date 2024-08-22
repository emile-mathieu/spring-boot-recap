package com.example.springbootrecap.dao;

import com.example.springbootrecap.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    public void create(Book newBook);
    public Optional<Book> findOne(String title);
    public List<Book> findAll();
}
