package ie.spring.lab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class LanguageTests {

    ApplicationContext context = SpringApplication.run(MainConfig.class);

    @Test
    public void messageSourceBeanExists() {
        Assertions.assertTrue(context.containsBean("messageSource"));
    }

    @Test
    public void french_correctText() {
        MessageSource messageSource =
                (MessageSource) context.getBean("messageSource");
        Assertions.assertEquals("Bienvenue dans le Planificateur de Mariage",
                messageSource.getMessage("greeting", null, Locale.FRENCH));
    }

    @Test
    public void default_correctText() {
        MessageSource messageSource =
                (MessageSource) context.getBean("messageSource");
        Assertions.assertEquals("Welcome to the Wedding Planner",
                messageSource.getMessage("greeting", null, Locale.getDefault()));
    }
}