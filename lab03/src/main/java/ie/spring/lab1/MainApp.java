package ie.spring.lab1;

import ie.spring.lab1.repositories.weddings.Wedding;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainConfig.class);
        WeddingRepository weddingRepository = context.getBean(WeddingRepository.class);

        Wedding wedding = weddingRepository.findById("RS342").orElseThrow(() ->
                new RuntimeException("Wedding RS342 not found"));

        System.out.println("Wedding Details:");
        System.out.println(wedding);

        System.out.println("\nSearching for wedding RS322:");
        weddingRepository.findById("RS322")
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Wedding RS322 not found")
                );

        if (!wedding.getGuests().isEmpty()) {
            System.out.println("\nGuest List:");
            wedding.getGuests().forEach(System.out::println);
        } else {
            System.out.println("Guest list is empty.");
        }

        System.out.println("\nCost per guest: " + wedding.getCostPerGuest());
        System.out.println("\nParticipants:");
        System.out.println("Person 1: " + wedding.getPerson1());
        System.out.println("Person 2: " + wedding.getPerson2());

        MessageSource messageSource = context.getBean(MessageSource.class);
        Locale defaultLocale = Locale.getDefault();
        Locale irish = new Locale("ga", "IRELAND");

        System.out.println("\nMessages:");
        System.out.println("Default Locale: " + messageSource.getMessage("greeting", null, defaultLocale));
        System.out.println("Irish Locale: " + messageSource.getMessage("greeting", null, irish));
    }
}