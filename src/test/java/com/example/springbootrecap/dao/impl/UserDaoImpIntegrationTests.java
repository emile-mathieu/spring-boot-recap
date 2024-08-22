package com.example.springbootrecap.dao.impl;

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
public class UserDaoImpIntegrationTests {

    @Autowired
    private UserDaoImpl underTest;

    @Test
    public void testThatUserCanBeCreatedAndRecalled() {
        // 1. Create a new user
        User newUser = User.builder()
                .id(3)
                .name("Josh")
                .email("josh@example.com")
                .build();
        underTest.create(newUser);
        Optional<User> userRetrieved = underTest.findOne(1L);

        // 2. Assert that the user was created and retrieved
        assertThat(userRetrieved).isPresent();
        assertThat(userRetrieved.get().getId()).isEqualTo(1L);
    }
    @Test public void testThatMultipleUsersCanBeRetrieved(){
        //SQL Users
        User newUser1 = User.builder()
                .id(1)
                .name("john_doe")
                .email("john@example.com")
                .build();
        User newUser2 = User.builder()
                .id(2)
                .name("bob_smith")
                .email("bob@example.com")
                .build();

        // Building 2 users
        User newUser3 = User.builder()
                .id(3)
                .name("Tom")
                .email("tom.example.com")
                .build();
        User newUser4 = User.builder()
                .id(4)
                .name("Jerry")
                .email("jerry.example.com")
                .build();

        // Creating the 2 users
        underTest.create(newUser3);
        underTest.create(newUser4);

        // 1. Retrieve all users
        List<User> users = underTest.findAll();
        // 2. Assert that the users were retrieved successfully
        assertThat(users).hasSize(4).containsExactly(newUser1, newUser2, newUser3, newUser4);
    }

}
