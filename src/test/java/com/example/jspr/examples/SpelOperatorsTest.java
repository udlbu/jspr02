package com.example.jspr.examples;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpelOperatorsTest {

    @Autowired
    private SpelOperators spelOperators;

    // Test Relational Operators

    @Test
    public void testIsGreaterThan() {
        assertTrue(spelOperators.isGreaterThan());
    }

    @Test
    public void testIsLessThan() {
        assertTrue(spelOperators.isLessThan());
    }

    @Test
    public void testIsEqual() {
        assertTrue(spelOperators.isEqual());
    }

    @Test
    public void testIsNotEqual() {
        assertTrue(spelOperators.isNotEqual());
    }

    // Test Logical Operators

    @Test
    public void testIsAndTrue() {
        assertTrue(spelOperators.isAndTrue());
    }

    @Test
    public void testIsOrTrue() {
        assertTrue(spelOperators.isOrTrue());
    }

    @Test
    public void testIsNotTrue() {
        assertFalse(spelOperators.isNotTrue());
    }

    // Test Assignment

    @Test
    public void testSum() {
        assertEquals(15, spelOperators.getSum());
    }

    @Test
    public void testTernaryResult() {
        assertEquals("Greater", spelOperators.getTernaryResult());
    }

    // Test Collection/Map Access
    @Test
    public void testContainsValue() {
        assertTrue(spelOperators.isContainsValue());
    }

    @Test
    public void testMapValue() {
        assertEquals("value2", spelOperators.getMapValue());
    }
}
