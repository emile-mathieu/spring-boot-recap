package com.example.springbootrecap.repositories;

import com.example.springbootrecap.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
    Iterable<User> findByAgeLessThan(int age);
    //Create customer Query
    @Query("SELECT u FROM User u WHERE u.age > ?1")
    Iterable<User> findUsersOlderThan(int age);
}
