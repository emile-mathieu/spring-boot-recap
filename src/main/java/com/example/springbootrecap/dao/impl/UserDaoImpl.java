package com.example.springbootrecap.dao.impl;

import com.example.springbootrecap.dao.UserDao;
import com.example.springbootrecap.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate template;

    public UserDaoImpl(final JdbcTemplate template){
        this.template = template;
    }

    @Override
    public void create(User newUser) {
        template.update(
                "INSERT INTO users (id, username, email) VALUES (?, ?, ?)",
                newUser.getId(), newUser.getName(), newUser.getEmail()
        );
    }

    @Override
    public Optional<User> findOne(Long id) {
        try {
            User user = template.queryForObject(
                    "SELECT id, username, email FROM users WHERE id = ?",
                    new Object[]{id},
                    (rs, rowNum) -> {
                        User u = new User();
                        u.setId(rs.getLong("id"));
                        u.setName(rs.getString("username"));
                        u.setEmail(rs.getString("email"));
                        return u;
                    }
            );
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
