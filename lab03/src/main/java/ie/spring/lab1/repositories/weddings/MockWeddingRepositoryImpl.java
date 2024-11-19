package ie.spring.lab1.repositories.weddings;

import ie.spring.lab1.repositories.basic.Person;
import lombok.ToString;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("default")
@Primary
public class MockWeddingRepositoryImpl implements WeddingRepository {
    private final Map<String, Wedding> weddings = new HashMap<>();

    private final List<Guest> guests = new ArrayList<>();

    public MockWeddingRepositoryImpl() {
        // Initialize sample data

        Guest g1 = new Guest(1L,
                new Person(20, "Fred", "O'Brien"),
                null,
                "089-6758475", "fobrien@gmail.com",
                "21 Argyle Road, Blackrock, Cork",
                "RS342");

        Guest g2 = new Guest(2L,
                new Person(20, "Laura", "Bellingham"),
                new Person(21, "Maura", "Murphy"),
                "087-7683451", "lorbell@gmail.com",
                "1 Green Park, Bishopstown, Cork",
                "RS342");

        guests.add(g1);
        guests.add(g2);

        Wedding wedding = new Wedding(
                "RS342",
                new Person(55, "Sharon", "Willis"),
                new Person(56, "Mark", "Wilson"),
                120.00,
                guests
        );

        weddings.put(wedding.getWeddingId(), wedding);
    }

    @Override
    public int getNumberOfGuests(String id) {
        return (int) (guests.stream()
                .filter(guest -> guest.getWeddingId().equals(id))
                .count() +
                guests.stream()
                        .filter(guest -> guest.getWeddingId().equals(id) && guest.getPlusOne() != null)
                        .count());
    }

    @Override
    public Optional<Wedding> findById(String id) {
        return Optional.ofNullable(weddings.get(id));
    }
}