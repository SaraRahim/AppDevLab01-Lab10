package ie.spring.lab1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfig.class, LanguageConfig.class})
@ComponentScan({"ie.spring.lab1"})
public class MainConfig {
}