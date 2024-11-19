package ie.spring.lab1.repositories.weddings;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("empty")
public class MockWeddingRepositoryImplEmpty implements WeddingRepository {

    @Override
    public Optional<Wedding> findById(String id) {
        return Optional.empty();
    }

    @Override
    public int getNumberOfGuests(String id) {
        return 0;
    }
}