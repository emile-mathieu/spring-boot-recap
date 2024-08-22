package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserDaoImpIntegrationTests {

    @Autowired
    private UserDaoImpl underTest;

    @Test
    public void testThatUserCanBeCreatedAndRecalled() {
        // 1. Create a new user
        User newUser = User.builder()
                .id(14)
                .name("Josh")
                .email("josh@example.com")
                .build();
        underTest.create(newUser);
        Optional<User> userRetrieved = underTest.findOne(1L);

        // 2. Assert that the user was created and retrieved
        assertThat(userRetrieved).isPresent();
        assertThat(userRetrieved.get().getId()).isEqualTo(1L);
    }

}
