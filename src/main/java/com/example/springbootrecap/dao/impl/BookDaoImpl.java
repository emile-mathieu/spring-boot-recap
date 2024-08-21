package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl {
    private final JdbcTemplate template;

    public BookDaoImpl(final JdbcTemplate template){
        this.template = template;
    }

    public void create(Book newBook) {
        template.update(
                "INSERT INTO books (id, title, author, user_id) VALUES (?, ?, ?, ?)",
                newBook.getId(), newBook.getTitle(), newBook.getAuthor(), newBook.getUser_id()
        );
    }
}
