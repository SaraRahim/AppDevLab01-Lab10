package ie.spring.lab1;

import ie.spring.lab1.MainConfig;
import ie.spring.lab1.services.CalculateCost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {MainConfig.class})
@TestPropertySource("classpath:application.properties")
public class Lab1ApplicationTests {

    @Autowired
    private CalculateCost calculateCost;

    @Autowired
    private MessageSource messageSource;

    @Test
    void testCalculateCostBeanIsNotNull() {
        assertNotNull(calculateCost, "The CalculateCost bean should not be null");
    }

    @Test
    void costCalculatesCorrectly() throws Exception {
        assertEquals(360, calculateCost.calculateWeddingCostExVat("RS342"),
                "Expected wedding cost (ex-VAT) should be 360");
    }

    @Test
    void costWeddingNotFound_thenThrowsException() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> calculateCost.calculateWeddingCostExVat("RS32"),
                "Expected an exception to be thrown for a non-existent wedding ID"
        );

        assertTrue(thrown.getMessage().contains("not found"),
                "The exception message should indicate that the wedding was not found");
    }

    @Test
    void costWeddingIncVat() throws Exception {
        assertEquals(414, calculateCost.calculateWeddingCostIncVat("RS342"),
                "Expected wedding cost (inc-VAT) should be 414");
    }

    @Test
    void testMessageSourceBean() {
        assertNotNull(messageSource, "The MessageSource bean should not be null");
    }

    @Test
    void testMessageForLocale() {
        Locale irish = new Locale("ga", "IRELAND");
        String message = messageSource.getMessage("welcome", null, irish);
        assertEquals("Fáilte go dtí an Pleanálaí Bainise", message,
                "Expected message should be in Irish for the locale ga_IRELAND");
    }
}