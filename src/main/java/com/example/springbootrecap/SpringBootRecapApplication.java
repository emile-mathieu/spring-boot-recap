package com.example.springbootrecap;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class SpringBootRecapApplication implements CommandLineRunner {

    private final DataSource datasource;

    public SpringBootRecapApplication(final DataSource dataSource ) {
        this.datasource = dataSource;
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRecapApplication.class, args);
    }

    @Override
    public void run(final String...args){
        log.info("DataSource : " + datasource.toString());
        final JdbcTemplate restTemplate = new JdbcTemplate(datasource);
        restTemplate.execute("select 1");
    }
}
