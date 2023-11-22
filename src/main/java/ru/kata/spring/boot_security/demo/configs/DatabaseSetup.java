package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSetup implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String[] args) {

        jdbcTemplate.execute("INSERT INTO roles (role) VALUES ('ROLE_ADMIN')");
        jdbcTemplate.execute("INSERT INTO roles (role) VALUES ('ROLE_USER')");

        jdbcTemplate.execute("INSERT INTO users (username, password, name, surname, age) " +
                "VALUES ('admin', '$2a$10$6oZiMiRBiHr2pvXA8YmAoOSfot7sDr1LDyIlGPeCPyR3YjQ7i7jfS', 'Admin', 'Adminskiy', 27)");
        jdbcTemplate.execute("INSERT INTO users (username, password, name, surname, age) " +
                "VALUES ('user', '$2a$10$hKszg5P0zTTOGW6RlbLZ3.wMYsvpxQ5w3NCzfD6P1zKwFYUAVhxIG', 'Leo', 'Messi', 36)");

        jdbcTemplate.execute("INSERT INTO users_roles (user_id, role_id) VALUES (1, 1)");
        jdbcTemplate.execute("INSERT INTO users_roles (user_id, role_id) VALUES (1, 2)");
        jdbcTemplate.execute("INSERT INTO users_roles (user_id, role_id) VALUES (2, 2)");
    }
}
