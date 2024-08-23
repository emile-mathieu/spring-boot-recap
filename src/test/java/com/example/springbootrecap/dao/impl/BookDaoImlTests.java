package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.domain.Book;
import com.example.springbootrecap.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookDaoImlTests {

    @Mock
    private JdbcTemplate template;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test void testThatCreatesBookGeneratedSQL() {
        //  Prepare mock to be tested
        Book newBook = Book.builder()
                .title("Living in Singapore")
                .author("Emile")
                .user_id(2L)
                .build();

        underTest.create(newBook);

        verify(template).update(
                eq("INSERT INTO books (title, author, user_id) VALUES (?, ?, ?)"),
                eq("Living in Singapore"), eq("Emile"), eq(2L)
        );
    }
    @Test
    public void testThatUpdatesBookGeneratedSQL() {
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title("Living in Singapore")
                .author("Emile")
                .user_id(2L)
                .build();

        // Act
        underTest.update(book);

        // Assert
        verify(template).update(
                eq("UPDATE books SET title = ?, author = ?, user_id = ? WHERE id = ?"),
                eq("Living in Singapore"), eq("Emile"), eq(2L), eq(1L)
        );
    }
    @Test
    public void testThatFindsOneBook(){
        Book mockBook = new Book(1L, "Living in Singapore", "Emile", 2L);

        when(template.queryForObject(anyString(), (Object[]) any(Object[].class), (RowMapper<Object>) any())).thenReturn(mockBook);

        underTest.findOne("Living in Singapore");

        verify(template).queryForObject(
                eq("SELECT id, title, author, user_id FROM books WHERE title = ?"),
                eq(new Object[]{"Living in Singapore"}),
                any(RowMapper.class)
        );
    }

    @Test
    public void testFindOneUnknownBook() {
        // Arrange
        String unknownTitle = "Doesn't exist";
        // thenThrow(new EmptyResultDataAccessException(1)
        when(template.queryForObject(anyString(), (Object[]) any(Object[].class), (RowMapper<Object>) any())).thenThrow(new EmptyResultDataAccessException(1));

        // Act
        Optional<Book> result = underTest.findOne(unknownTitle);

        // Assert
        assertFalse(result.isPresent(), "Expected an empty Optional when the user is not found.");
    }
    @Test
    public void testThatFindsAllBooks(){
        underTest.findAll();

        verify(template).query(
                eq("SELECT id, title, author, user_id FROM books"),
                any(RowMapper.class)
        );
    }
}
