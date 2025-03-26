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
import static workshop.springb.starter.aop.AdviceConstans.AFTER;

/*
    TODO 1c: Zapoznaj się z logiką testu.

    Testy sprawdzają działanie aspektu @After w metodach zaczynających się od "Gr":

    1. **beforeEach()**: Ustawia przechwytywanie wyjściowego strumienia, aby zarejestrować logi.

    2. **Test 1: after_methodsStartWithGr_OK**:
       - Wysyła GET do "/greet" z poprawnymi parametrami, oczekuje statusu 200 i greeting "Hello, X!" w odpowiedzi.
       - Sprawdza, czy logi zawierają 3 x 'method-execution'

    3. **Test 2: after_methodsStartWithGr_EXCEPTION**:
       - Wysyła GET do "/greet" z parametrem `name = "ex"`, oczekuje statusu 500 i komunikatu o błędzie.
       - Sprawdza, czy logi zawierają 3 x "method-execution".


   TODO 1d Pytanie:

   Dlaczego w after_methodsStartWithGr_OK test sprawdza, czy AfterAspect#logAdvice będzie wyołany 3 razy a nie 4?
   (pointcut AppPointcuts#methodsStartWithGr spradza metody zaczynające się na gr)
*/

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(AFTER)
class AfterAspectTest {

    private static final String EXPECTED_JOINPOINT_KIND = "method-execution";
    private static final String EXPECTED_OUTPUT = String.format(
            "%s %s%n%s %s%n%s %s%n", AFTER, EXPECTED_JOINPOINT_KIND, AFTER, EXPECTED_JOINPOINT_KIND, AFTER, EXPECTED_JOINPOINT_KIND
    );


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("@After on methods starting with 'Gr' - Successful request")
    void after_methodsStartWithGr_OK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("name", "X")
                        .param("isFormal", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting").value("Hello, X!"));

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    @DisplayName("@After on methods starting with 'Gr' - Exception case")
    void after_methodsStartWithGr_EXCEPTION() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("name", "ex")
                        .param("isFormal", "true"))
                .andExpect(status().is5xxServerError())
                .andExpect(result -> assertEquals(
                        "Probably request's param 'name' has an 'ex' value :)",
                        result.getResponse().getContentAsString()
                ));

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }
}