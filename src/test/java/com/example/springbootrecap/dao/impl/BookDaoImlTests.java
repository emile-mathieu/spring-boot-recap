package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImlTests {

    @Mock
    private JdbcTemplate template;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test void testThatCreatesBookGeneratedSQL() {
        //  Prepare mock to be tested
        Book newBook = Book.builder()
                .id(1L)
                .title("Living in Singapore")
                .author("Emile")
                .user_id(1L)
                .build();

        underTest.create(newBook);

        verify(template).update(
                eq("INSERT INTO books (id, title, author, user_id) VALUES (?, ?, ?, ?)"),
                eq(1L), eq("Living in Singapore"), eq("Emile"), eq(1L)
        );
    }
}
