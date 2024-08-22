package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BookDaoImpIntegrationTests {

    @Autowired
    private BookDaoImpl underTest;


    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        // 1. Create a new book
        Book newBook = Book.builder()
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .user_id(1L)
                .build();
        underTest.create(newBook);
        Optional<Book> bookRetrieved = underTest.findOne("The Great Gatsby");

        // 2. Assert that the book was created and retrieved
        assertThat(bookRetrieved).isPresent();
        assertThat(bookRetrieved.get().getTitle()).isEqualTo("The Great Gatsby");
    }

}
