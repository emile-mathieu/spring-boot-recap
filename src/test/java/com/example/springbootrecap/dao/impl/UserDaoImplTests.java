package com.example.springbootrecap.dao.impl;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDaoImplTests {

    @Mock
    private JdbcTemplate template; // Mock JdbcTemplate

    @InjectMocks
    private UserDaoImpl underTest; // Inject the mock into UserDaoImpl

    @Test
    public void testThatCreatesUserGeneratedSql() {
        User newUser = User.builder()
                .id(1L)
                .name("Josh")
                .email("josh@example.com")
                .build();

        underTest.create(newUser); // This should now use the mocked JdbcTemplate

        verify(template).update(
                eq("INSERT INTO users (id, username, email) VALUES (?, ?, ?)"),
                eq(1L), eq("Josh"), eq("josh@example.com")
        );
    }
    @Test
    public void testThatReadsUserGeneratedSql(){
        User mockUser = new User(1L, "Josh", "josh@example.com");

        when(template.queryForObject(anyString(), (Object[]) any(Object[].class), (RowMapper<Object>) any())).thenReturn(mockUser);

        underTest.findOne(1L);

        verify(template).queryForObject(
                eq("SELECT id, username, email FROM users WHERE id = ?"),
                eq(new Object[]{1L}),
                any(RowMapper.class)
        );
    }

    @Test
    public void testFindOneUnknownUser() {
        // Arrange
        Long unknownUserId = 999L;
//        thenThrow(new EmptyResultDataAccessException(1)
        when(template.queryForObject(anyString(), (Object[]) any(Object[].class), (RowMapper<Object>) any())).thenThrow(new EmptyResultDataAccessException(1));

        // Act
        Optional<User> result = underTest.findOne(unknownUserId);

        // Assert
        assertFalse(result.isPresent(), "Expected an empty Optional when the user is not found.");
    }
    @Test
    public void findAllUsersGeneratedSql() {
        underTest.findAll();
        verify(template).query(
                eq("SELECT id, username, email FROM users"),
                any(RowMapper.class)
        );
    }
}
