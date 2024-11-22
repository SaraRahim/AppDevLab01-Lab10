package ie.spring.jdbc;

import ie.spring.jdbc.configurations.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.Locale;

@SpringJUnitConfig(Config.class)
class DemoApplicationTests {

    @Autowired
    ApplicationContext context;

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    MessageSource messageSource;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(context, "ApplicationContext should not be null");
    }

    @Test
    void dataSourceBeanExists() {
        Assertions.assertNotNull(dataSource, "DataSource bean should not be null");
    }

    @Test
    void jdbcTemplateBeanExists() {
        Assertions.assertNotNull(jdbcTemplate, "JdbcTemplate bean should not be null");
    }

    @Test
    void namedParameterJdbcTemplateBeanExists() {
        Assertions.assertNotNull(namedParameterJdbcTemplate, "NamedParameterJdbcTemplate bean should not be null");
    }

    @Test
    void messageSourceBeanExists() {
        Assertions.assertNotNull(messageSource, "MessageSource bean should not be null");
    }

    @Test
    void testMessageSourceForDefaultLocale() {
        String message = messageSource.getMessage("welcome", null, Locale.getDefault());
        Assertions.assertEquals("Welcome to the Cartoon Database", message, "Message should match for default locale");
    }

    @Test
    void testMessageSourceForIrishLocale() {
        Locale irish = new Locale("ga", "IE");
        String message = messageSource.getMessage("welcome", null, irish);
        Assertions.assertEquals("Fáilte chuig an mBunachar Cartún", message, "Message should match for Irish locale");
    }
}