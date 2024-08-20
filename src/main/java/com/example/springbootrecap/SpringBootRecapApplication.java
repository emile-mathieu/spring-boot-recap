package com.example.springbootrecap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringBootRecapApplication {

    private final DataSource datasource;

    public SpringBootRecapApplication(final DataSource dataSource ) {
        this.datasource = dataSource;
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRecapApplication.class, args);
    }
}
