package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.dao.UserDao;
import com.example.springbootrecap.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate template;

    public UserDaoImpl(final JdbcTemplate template){
        this.template = template;
    }

    @Override
    public void create(User newUser) {
        template.update(
                "INSERT INTO users (id, name, email) VALUES (?, ?, ?)",
                newUser.getId(), newUser.getName(), newUser.getEmail()
        );
    }
}
