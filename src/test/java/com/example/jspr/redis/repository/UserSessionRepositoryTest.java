package com.example.jspr.redis.repository;


import com.example.jspr.redis.model.UserSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataRedisTest
class UserSessionRepositoryTest {

    @Autowired
    private UserSessionRepository repository;

    @Test
    void testSaveAndFindSession() {
        UserSession session = new UserSession("123", "testData");
        repository.save(session);

        assertThat(repository.findById("123")).isPresent()
                .get().extracting(UserSession::getData).isEqualTo("testData");
    }
}
