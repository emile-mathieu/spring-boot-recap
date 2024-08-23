package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.dao.BookDao;
import com.example.springbootrecap.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate template;

    public BookDaoImpl(final JdbcTemplate template){
        this.template = template;
    }

    public void create(Book newBook) {
        template.update(
                "INSERT INTO books (title, author, user_id) VALUES (?, ?, ?)",
                 newBook.getTitle(), newBook.getAuthor(), newBook.getUser_id()
        );
    }

    @Override
    public void update(Book book) {
        template.update(
                "UPDATE books SET title = ?, author = ?, user_id = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getUser_id(), book.getId()
        );
    }

    @Override
    public void delete(Long id) {
        template.update(
                "DELETE FROM books WHERE id = ?",
                id
        );
    }

    @Override
    public Optional<Book> findOne(String title) {
        try{
            Book book = template.queryForObject("SELECT id, title, author, user_id FROM books WHERE title = ?",
                    new Object[]{title},
                    (rs, rowNum) -> new Book(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getLong("user_id")
                    )
            );
            return Optional.of(book);
        } catch (Exception e){
        return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = template.query("SELECT id, title, author, user_id FROM books",
                (rs, rowNum) -> new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getLong("user_id")
                )
        );
        return books;
    }
}
