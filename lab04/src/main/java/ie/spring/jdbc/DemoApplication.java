package ie.spring.jdbc;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.repositories.CartoonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

        DataSource dataSource = applicationContext.getBean(DataSource.class);
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = applicationContext.getBean(NamedParameterJdbcTemplate.class);
        MessageSource messageSource = applicationContext.getBean(MessageSource.class);

        CartoonRepository cartoonRepository = applicationContext.getBean(CartoonRepository.class);

        Locale irishLocale = new Locale("ga", "IE");
        System.out.println("Default Locale Message: " + messageSource.getMessage("welcome", null, Locale.getDefault()));
        System.out.println("Irish Locale Message: " + messageSource.getMessage("welcome", null, irishLocale));

        List<Map<String, Object>> cartoonsRaw = jdbcTemplate.queryForList("SELECT * FROM cartoons");
        System.out.println("\nCartoons in Database (Raw Query):");
        cartoonsRaw.forEach(System.out::println);

        List<Cartoon> cartoons = cartoonRepository.findAll();
        System.out.println("\nCartoons in Database (Repository):");
        cartoons.forEach(System.out::println);

        String sql = "SELECT * FROM cartoons WHERE cartoon_name = :name";
        Map<String, Object> params = Map.of("name", "SpongeBob SquarePants");
        List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(sql, params);

        System.out.println("\nQuery Result for 'SpongeBob SquarePants':");
        result.forEach(System.out::println);
    }
}