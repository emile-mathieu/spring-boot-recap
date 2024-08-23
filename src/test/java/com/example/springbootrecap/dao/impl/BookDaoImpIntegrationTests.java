package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.domain.Book;
import com.example.springbootrecap.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookDaoImpIntegrationTests {

    @Autowired
    private BookDaoImpl underTest;

    @Autowired
    private UserDaoImpl userDaoImpl;


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
    @Test
    public void testThatBookCanBeUpdated() {
        // 1. Create a new book
        Book newBook = Book.builder()
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .user_id(1L)
                .build();
        underTest.create(newBook);

        // 2. Update the book
        Book updatedBook = Book.builder()
                .id(1L)
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .user_id(2L)
                .build();
        underTest.update(updatedBook);

        // 3. Retrieve the updated book
        Optional<Book> bookRetrieved = underTest.findOne("The Great Gatsby");

        // 4. Assert that the book was updated
        assertThat(bookRetrieved).isPresent();
        assertThat(bookRetrieved.get().getUser_id()).isEqualTo(2L);
    }


    @Test
    public void testThatAllBooksCanBeRetrieved() {
        // Create users
        userDaoImpl.create(User.builder()
                .id(3L)
                .name("harold")
                .email("harold@example.com")
                .build());
        userDaoImpl.create(User.builder()
                .id(4L)
                .name("jane")
                .email("jane@example.com")
                .build());
        userDaoImpl.create(User.builder()
                .id(5L)
                .name("Tom")
                .email("tom@example.com")
                .build());

        // Create books
        Book book1 = Book.builder()
                .id(1L)
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .user_id(1L)
                .build();
        Book book2 = Book.builder()
                .id(2L)
                .title("The Catcher in the Rye")
                .author("J.D. Salinger")
                .user_id(4L)
                .build();
        Book book3 = Book.builder()
                .id(3L)
                .title("To Kill a Mockingbird")
                .author("Harper Lee")
                .user_id(5L)
                .build();

        underTest.create(book1);
        underTest.create(book2);
        underTest.create(book3);

        // 1. Retrieve all books
        List<Book> books = underTest.findAll();

        // 2. Assert that all books were retrieved
        assertThat(books).hasSize(3).containsExactly(book1, book2, book3);
    }

}
