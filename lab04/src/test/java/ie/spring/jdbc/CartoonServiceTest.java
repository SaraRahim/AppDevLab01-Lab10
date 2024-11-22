package ie.spring.jdbc;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.exceptions.DataConflictException;
import ie.spring.jdbc.exceptions.MalformedDataException;
import ie.spring.jdbc.exceptions.NotFoundException;
import ie.spring.jdbc.services.CartoonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CartoonServiceTest {

    @Autowired
    private CartoonService cartoonService;

    @Test
    public void findById_shouldReturnCartoon() throws NotFoundException {
        Cartoon cartoon = cartoonService.findByCartoonId(2);
        Assertions.assertEquals("Pikachu", cartoon.getCartoonName(), "Cartoon name should match expected value");
    }

    @Test
    public void findById_notFound_shouldThrowNotFoundException() throws NotFoundException {
        Assertions.assertThrows(NotFoundException.class, () -> {
            cartoonService.findByCartoonId(111);
        }, "Should throw NotFoundException for a non-existent ID");
    }

    @Test
    public void findAll_shouldReturnAllCartoons() {
        List<Cartoon> cartoons = cartoonService.findAll();
        Assertions.assertEquals(10, cartoons.size(), "Cartoon list size should match the expected value");
    }

    @Test
    public void count_shouldReturnCountOfCartoons() {
        int count = cartoonService.count();
        Assertions.assertEquals(10, count, "Cartoon count should match the expected value");
    }

    @Test
    public void save_idAlreadyExists_shouldThrowDataConflictException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(1, "Muggles Unit", 2024);
        Assertions.assertThrows(DataConflictException.class, () -> {
            cartoonService.save(cartoon);
        }, "Should throw DataConflictException when ID already exists");
    }

    @Test
    public void save_noName_shouldThrowMalformedDataException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(11, "", 2024);
        Assertions.assertThrows(MalformedDataException.class, () -> {
            cartoonService.save(cartoon);
        }, "Should throw MalformedDataException when name is empty");
    }

    @Test
    public void save_nullName_shouldThrowMalformedDataException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(11, null, 2024);
        Assertions.assertThrows(MalformedDataException.class, () -> {
            cartoonService.save(cartoon);
        }, "Should throw MalformedDataException when name is null");
    }

    @Test
    public void delete_shouldDeleteCartoon() throws NotFoundException {
        cartoonService.delete(2);
        Assertions.assertThrows(NotFoundException.class, () -> {
            cartoonService.findByCartoonId(2);
        }, "Should throw NotFoundException after deleting cartoon with ID 2");
    }

    @Test
    public void save_badYear_shouldThrowMalformedDataException() throws DataConflictException, MalformedDataException {
        Cartoon cartoon = new Cartoon(11, "Muggles Unite", 3000);
        Assertions.assertThrows(MalformedDataException.class, () -> {
            cartoonService.save(cartoon);
        }, "Should throw MalformedDataException when year is invalid (e.g., too far in the future)");
    }
}