package com.example.jspr.examples;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpelDataAccessTest {

    @Autowired
    private SpelDataAccess spelAccessData;

    @Test
    public void testStringLength() {
        assertEquals(11, spelAccessData.getStringLength());
    }

    @Test
    public void testFruit() {
        assertEquals("Banana", spelAccessData.getFruit());
    }

    @Test
    public void testSecondFruit() {
        assertEquals("Cherry", spelAccessData.getSecondFruit());
    }

    @Test
    public void testMapValue() {
        assertEquals("value1", spelAccessData.getMapValue());
    }

    @Test
    public void testNumberList() {
        assertEquals(5, spelAccessData.getNumberList().size());
        assertTrue(spelAccessData.getNumberList().contains(3));
    }

    @Test
    public void testUserInfo() {
        assertEquals("John", spelAccessData.getUserInfo().get("name"));
        assertEquals(30, spelAccessData.getUserInfo().get("age"));
        assertEquals("New York", spelAccessData.getUserInfo().get("city"));
    }

    @Test
    public void testNumberArray() {
        assertEquals(5, spelAccessData.getNumberArray().length);
        assertEquals(3, spelAccessData.getNumberArray()[2]);
    }

    @Test
    public void testUserName() {
        assertEquals("Alice", spelAccessData.getUserName());
    }

    @Test
    public void testFirstUserName() {
        assertEquals("Bob", spelAccessData.getFirstUserName());
    }

    @Test
    public void testUserCity() {
        assertEquals("Madrid", spelAccessData.getUserCity());
    }
}
