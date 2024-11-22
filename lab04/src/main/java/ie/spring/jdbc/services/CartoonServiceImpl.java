package ie.spring.jdbc.services;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.exceptions.DataConflictException;
import ie.spring.jdbc.exceptions.MalformedDataException;
import ie.spring.jdbc.exceptions.NotFoundException;
import ie.spring.jdbc.repositories.CartoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CartoonServiceImpl implements CartoonService {

    private final CartoonRepository cartoonRepository;

    @Autowired
    public CartoonServiceImpl(CartoonRepository cartoonRepository) {
        this.cartoonRepository = cartoonRepository;
    }

    @Override
    public int count() {
        return cartoonRepository.count();
    }

    @Override
    public List<Cartoon> findAll() {
        return cartoonRepository.findAll();
    }

    @Override
    public Cartoon findByCartoonId(int cartoon_id) throws NotFoundException {
        return cartoonRepository.findByCartoonId(cartoon_id).orElseThrow(
                () -> new NotFoundException("Cartoon with ID " + cartoon_id + " not found"));
    }

    @Override
    public void delete(int cartoon_id) throws NotFoundException {
        if (cartoonRepository.findByCartoonId(cartoon_id).isEmpty()) {
            throw new NotFoundException("Cartoon with ID " + cartoon_id + " not found");
        }
        cartoonRepository.delete(cartoon_id);
    }

    @Transactional
    public void save(Cartoon cartoon) throws DataConflictException, MalformedDataException {
        if (cartoonRepository.findByCartoonId(cartoon.getCartoonId()).isPresent()) {
            throw new DataConflictException("Duplicate cartoon ID.");
        }
        if (cartoon.getFirstAppearanceYear() > LocalDate.now().getYear()) {
            throw new MalformedDataException("First appearance year cannot be in the future.");
        }
        if (cartoon.getCartoonName() == null || cartoon.getCartoonName().isEmpty()) {
            throw new MalformedDataException("Cartoon name cannot be null or empty.");
        }
        cartoonRepository.save(cartoon);
    }
}