package com.example.jspr.sql.repository;



import com.example.jspr.sql.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnabledIfSystemProperty(named = "docker", matches = "true") // Ensures Docker is running
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    static void checkDockerRunning() {
        try {
            Process process = Runtime.getRuntime().exec("docker ps");
            process.waitFor();
        } catch (Exception e) {
            throw new IllegalStateException("Docker must be running for this test!");
        }
    }

    @Test
    void shouldFindAllUsers() {
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size()); // Matches data from `init.sql`
    }
}
