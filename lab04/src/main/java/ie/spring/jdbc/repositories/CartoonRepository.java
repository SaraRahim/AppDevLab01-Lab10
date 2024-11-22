package ie.spring.jdbc.repositories;

import ie.spring.jdbc.entities.Cartoon;
import java.util.List;
import java.util.Optional;

public interface CartoonRepository {
    int count();
    Optional<Cartoon> findByCartoonId(int cartoonId);
    List<Cartoon> findAll();
    void delete(int cartoonId);
    void save(Cartoon cartoon);
}