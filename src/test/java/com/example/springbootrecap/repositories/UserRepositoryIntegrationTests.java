package com.example.springbootrecap.repositories;

import com.example.springbootrecap.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTests {

    @Autowired
    private UserRepository underTest;

    @Test
    public void testThatUserCanBeCreatedAndRecalled() {
        // 1. Create a new user
        User newUser = User.builder()
                .id(1L)
                .name("Josh")
                .age(22)
                .email("josh@example.com")
                .build();
        underTest.save(newUser);
        Optional<User> userRetrieved = underTest.findById(newUser.getId());

        // 2. Assert that the user was created and retrieved
        assertThat(userRetrieved).isPresent();
        assertThat(userRetrieved.get().getId()).isEqualTo(1L);
    }
    @Test public void testThatMultipleUsersCanBeRetrieved(){
        User newUser1 = User.builder()
                .id(1)
                .name("john_doe")
                .age(25)
                .email("john@example.com")
                .build();
        User newUser2 = User.builder()
                .id(2)
                .name("bob_smith")
                .age(30)
                .email("bob@example.com")
                .build();
        User newUser3 = User.builder()
                .id(3)
                .name("Tom")
                .age(35)
                .email("tom.example.com")
                .build();
        User newUser4 = User.builder()
                .id(4)
                .name("Jerry")
                .age(40)
                .email("jerry.example.com")
                .build();

        // Creating the 2 users
        underTest.save(newUser1);
        underTest.save(newUser2);
        underTest.save(newUser3);
        underTest.save(newUser4);
        // 1. Retrieve all users
        Iterable<User> users = underTest.findAll();
        // 2. Assert that the users were retrieved successfully
        assertThat(users).hasSize(4).containsExactly(newUser1, newUser2, newUser3, newUser4);
    }
    @Test
    public void testThatUserCanBeUpdated() {
        // 1. Create a new user
        User newUser = User.builder()
                .id(1L)
                .name("Josh")
                .age(22)
                .email("josh@example.com")
                .build();
        underTest.save(newUser);

        // 2. Update the user
        newUser.setName("Joshua");
        underTest.save(newUser);

        // 3. Retrieve the updated user
        Optional<User> userRetrieved = underTest.findById(newUser.getId());

        // 4. Assert that the user was updated
        assertThat(userRetrieved.get().equals(newUser));
    }
    @Test public void testUserCanBeDeleted(){
        // 1. Create a new user
        User newUser = User.builder()
                .id(1L)
                .name("Josh")
                .age(22)
                .email("josh@example.com")
                .build();

        underTest.save(newUser);
        underTest.delete(newUser);
        assertThat(underTest.findById(newUser.getId())).isEmpty();
    }
    @Test public void userCanBeFoundByName() {
        User newUser = User.builder()
                .id(1L)
                .name("Josh")
                .age(22)
                .email("josh@example.com")
                .build();
        underTest.save(newUser);
        assertThat(underTest.findByName("Josh")).isEqualTo(newUser);
    }
    @Test public void usersCanBeFoundByAgeLessThan() {
        User newUser1 = User.builder()
                .id(1)
                .name("john_doe")
                .age(25)
                .email("john_doe@example.com")
                .build();
        User newUser2 = User.builder()
                .id(2)
                .name("Emile")
                .age(21)
                .email("emile@example.com")
                .build();
        User newUser3 = User.builder()
                .id(3)
                .name("Darren")
                .age(30)
                .email("darren@example.com")
                .build();

        underTest.save(newUser1);
        underTest.save(newUser2);
        underTest.save(newUser3);

        assertThat(underTest.findByAgeLessThan(30)).containsExactly(newUser1, newUser2);
    }
    @Test public void usersCanBeFoundByAgeGreaterThan() {
        User newUser1 = User.builder()
                .id(1)
                .name("john_doe")
                .age(25)
                .email("jhon_doe@example.com")
                .build();
        User newUser2 = User.builder()
                .id(2)
                .name("emile")
                .age(21)
                .email("emile@example.com")
                .build();

        underTest.save(newUser1);
        underTest.save(newUser2);

        assertThat(underTest.findUsersOlderThan(21)).containsExactly(newUser1);
    }

}
