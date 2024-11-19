package ie.spring.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ie.spring.lab1.repositories.weddings.MockWeddingRepositoryImpl;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.CalculateCost;
import ie.spring.lab1.services.CalculateCostImplementation;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class AppConfig {

    @Bean
    public WeddingRepository weddingRepository() {
        return new MockWeddingRepositoryImpl();
    }

    @Bean
    public CalculateCost calculateCost(WeddingRepository weddingRepository) {
        return new CalculateCostImplementation(weddingRepository);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}