package com.example.springbootrecap.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl {
    private final JdbcTemplate template;

    public BookDaoImpl(final JdbcTemplate template){
        this.template = template;
    }
}
