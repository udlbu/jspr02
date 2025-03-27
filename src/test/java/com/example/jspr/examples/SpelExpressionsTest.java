package com.example.jspr.examples;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpelExpressionsTest {

    @Autowired
    private SpelExpressions spelExpressions;

    @Test
    public void testPiValue() {
        assertEquals(Math.PI, spelExpressions.getPiValue());
    }

    @Test
    public void testGreeting() {
        assertEquals("Hello World", spelExpressions.getGreeting());
    }

    @Test
    public void testSubstring() {
        assertEquals("Hel", spelExpressions.getSubstring());
    }

    @Test
    public void testSum() {
        assertEquals(8, spelExpressions.getSum());
    }

    @Test
    public void testDifference() {
        assertEquals(8, spelExpressions.getDifference());
    }

    @Test
    public void testMatchesPattern() {
        assertTrue(spelExpressions.isMatchesPattern());
    }

    @Test
    public void testCleanedString() {
        assertEquals("abc", spelExpressions.getCleanedString());
    }

    @Test
    public void testSystemUserName() {
        assertNotNull(spelExpressions.getSystemUserName());
    }
}
