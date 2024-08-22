package com.example.springbootrecap.dao;

import com.example.springbootrecap.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void create(User newUser);
    Optional<User> findOne(Long id);

    List<User> findAll();
}