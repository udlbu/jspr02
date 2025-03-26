package com.example.jspr.event;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private CustomPublisher publisher;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCustomEventLogging() throws InterruptedException {
        publisher.publishCustomEvent("Test event message");

        // TODO 1 uruchom test bez modyfikacji
        // TODO 2 odkomentuj poniższą linijkę i uruchom jeszcze raz
        // TODO 3 znów zakomentuj, tym razem zakomentuj @Configuration w AsyncConfig (Spring użyje domyślnego w tym samym wątku)
        // Thread.sleep(1000);


        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Publishing event from thread"));
        assertTrue(consoleOutput.contains("Received CustomEvent: Test event message"));
    }
}