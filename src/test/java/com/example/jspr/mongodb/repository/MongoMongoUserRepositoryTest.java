package com.example.jspr.mongodb.repository;


import com.example.jspr.mongodb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class MongoMongoUserRepositoryTest {

    @Autowired
    private MongoUserRepository repository;

    @Test
    void testSaveAndFindUser() {
        User user = new User("Alice");
        repository.save(user);

        assertThat(repository.findAll()).isNotEmpty()
                .anyMatch(u -> u.getName().equals("Alice"));
    }
}
