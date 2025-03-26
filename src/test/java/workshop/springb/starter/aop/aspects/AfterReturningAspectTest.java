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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static workshop.springb.starter.aop.AdviceConstans.AFTER_RETURNING;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(AFTER_RETURNING)
class AfterReturningAspectTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("@AfterReturning on methods in Loggable-annotated classes - Successful request")
    void afterReturning_methodsInLoggableAnnotatedClasses_OK() throws Exception {
        String expectedOutput = AFTER_RETURNING + " GreetSubservice" + System.lineSeparator();

        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("name", "X")
                        .param("isFormal", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting").value("Hello, X!"));

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("@AfterReturning on methods in Loggable-annotated classes - Exception case")
    void afterReturning_methodsInLoggableAnnotatedClasses_Exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("name", "ex")
                        .param("isFormal", "true"))
                .andExpect(status().is5xxServerError())
                .andExpect(result ->
                        assertEquals("Probably request's param 'name' has an 'ex' value :)",
                                result.getResponse().getContentAsString()));

        assertEquals("", outContent.toString());
    }
}