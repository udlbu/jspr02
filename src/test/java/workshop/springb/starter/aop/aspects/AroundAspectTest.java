package workshop.springb.starter.aop.aspects;

import org.glassfish.jaxb.core.v2.TODO;
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
import static workshop.springb.starter.aop.AdviceConstans.AROUND;

/*
    TODO 6 To wszystko, mamy już gotowy test, który możemy uruchomić.  \( ﾟヮﾟ)/
    Przed uruchomieniem przejrzyjmy test.

    Uruchom test, następnie zrób zmianę a qspekcie Arround, żeby test nie przeszedł i uruchom ponownie
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(AROUND)
class AroundAspectTest {

    // Przechwycenie logów System.out
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Sprawdzenie @Around dla Pointcut methodsInServicePackage()")
    void around_methodsInServicePackage() throws Exception {
        // Oczekiwane logi
        String expectedOut = AroundAspect.AROUND_BEFORE_MSG + System.lineSeparator()
                + AroundAspect.AROUND_AFTER_MSG + System.lineSeparator();

        // Wywołanie endpointu
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("name", "X")
                        .param("isFormal", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting").value("Hello, X!"));

        // Weryfikacja działania aspektu
        assertEquals(expectedOut, outContent.toString());
    }
}