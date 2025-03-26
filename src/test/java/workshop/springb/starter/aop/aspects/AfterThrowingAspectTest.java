package workshop.springb.starter.aop.aspects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static workshop.springb.starter.aop.AdviceConstans.AFTER_THROWING;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(AFTER_THROWING)
class AfterThrowingAspectTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("@AfterThrowing on methods in GreetSubservice - Successful request")
    void afterThrowing_methodsInGreetSubservice_OK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("name", "X")
                        .param("isFormal", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting").value("Hello, X!"));

        assertEquals("", outContent.toString());
    }

    @Test
    @DisplayName("@AfterThrowing on methods in GreetSubservice - Exception thrown")
    void afterThrowing_methodsInGreetSubservice_Exception() throws Exception {
        String expectedOutput = AFTER_THROWING + " 2" + System.lineSeparator();

        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("name", "ex")
                        .param("isFormal", "true"))
                .andExpect(status().is5xxServerError())
                .andExpect(result ->
                        assertEquals("Probably request's param 'name' has an 'ex' value :)",
                                result.getResponse().getContentAsString()));

        assertEquals(expectedOutput, outContent.toString());
    }
}