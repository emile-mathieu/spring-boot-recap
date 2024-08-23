package com.example.springbootrecap.repositories;

import com.example.springbootrecap.domain.Book;
import com.example.springbootrecap.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookDaoImpIntegrationTests {
    @Autowired
    private BookRepository underTest;

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        // 1. Create new User and associated new book
        User newUser = User.builder()
                .id(1L)
                .name("Emile")
                .email("emile@example.com")
                .build();
        Book newBook = Book.builder()
                .id(1L)
                .title("Living in Singapore.")
                .author(newUser)
                .build();
        underTest.save(newBook);
        Optional<Book> bookRetrieved = underTest.findById(newBook.getId());

        // 2. Assert that the book was created and retrieved
        assertThat(bookRetrieved.get().equals(newBook));
    }
    @Test
    public void testThatBookCanBeUpdated() {
        // Create a new user
        User newUser = User.builder()
                .id(1L)
                .name("Emile")
                .email("emile@example.com")
                .build();

        // 1. Create a new book
        Book newBook = Book.builder()
                .id(1L)
                .title("The Great Gatsby")
                .author(newUser)
                .build();
        underTest.save(newBook);
        newBook.setTitle("Living in Singapore.");
        underTest.save(newBook);
        // 3. Retrieve the updated book
        Optional<Book> bookRetrieved = underTest.findById(newBook.getId());
        // 4. Assert that the book was updated
        assertThat(bookRetrieved).isPresent();
        assertThat(bookRetrieved.get().getTitle()).isEqualTo(newBook.getTitle());
    }
    @Test
    public void testThatBookCanBeDeleted() {
        // Create a new user
        User newUser = User.builder()
                .id(1L)
                .name("Emile")
                .email("emile@example.com")
                .build();

        // 1. Create a new book
        Book newBook = Book.builder()
                .id(1L)
                .title("The Great Gatsby")
                .author(newUser)
                .build();
        underTest.save(newBook);
        // 2. Delete the book
        underTest.delete(newBook);
        // 3. Retrieve the book
        Optional<Book> bookRetrieved = underTest.findById(newBook.getId());
        // 4. Assert that the book was deleted
        assertThat(bookRetrieved).isEmpty();
    }

    @Test
    public void testThatAllBooksCanBeRetrieved() {
        // Create users
        User userOne = User.builder()
                .id(1L)
                .name("harold")
                .email("harold@example.com")
                .build();
        User userTwo = User.builder()
                .id(2L)
                .name("jane")
                .email("jane@example.com")
                .build();

        // Create books
        Book book1 = Book.builder()
                .id(1L)
                .title("The Great Gatsby")
                .author(userOne)
                .build();
        Book book2 = Book.builder()
                .id(2L)
                .title("The Catcher in the Rye")
                .author(userOne)
                .build();
        Book book3 = Book.builder()
                .id(3L)
                .title("To Kill a Mockingbird")
                .author(userTwo)
                .build();

        underTest.save(book1);
        underTest.save(book2);
        underTest.save(book3);

        // 1. Retrieve all books
        Iterable<Book> books = underTest.findAll();

        // 2. Assert that all books were retrieved
        assertThat(books).hasSize(3).containsExactly(book1, book2, book3);
    }

}
