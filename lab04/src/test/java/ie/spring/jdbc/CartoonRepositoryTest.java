package ie.spring.jdbc;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.repositories.CartoonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CartoonRepositoryTest {

    @Autowired
    private CartoonRepository cartoonRepository;

    @Test
    public void testCount() {
        int count = cartoonRepository.count();
        assertEquals(10, count, "Cartoon count should be 10");
    }

    @Test
    public void testFindAll() {
        List<Cartoon> cartoons = cartoonRepository.findAll();
        assertEquals(10, cartoons.size(), "Cartoons list size should be 10");
    }

    @Test
    public void testFindByCartoonId() {
        Cartoon cartoon = cartoonRepository.findByCartoonId(1).orElse(null);
        assert cartoon != null;
        assertEquals("SpongeBob SquarePants", cartoon.getCartoonName());
    }
}