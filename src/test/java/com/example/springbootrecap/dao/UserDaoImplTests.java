package com.example.springbootrecap.dao;

import com.example.springbootrecap.dao.impl.UserDaoImpl;
import com.example.springbootrecap.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

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
                eq("INSERT INTO users (id, name, email) VALUES (?, ?, ?)"),
                eq(1L), eq("Josh"), eq("josh@example.com")
        );
    }
}
