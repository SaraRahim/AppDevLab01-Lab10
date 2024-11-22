package ie.spring.jdbc.services;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.exceptions.DataConflictException;
import ie.spring.jdbc.exceptions.MalformedDataException;
import ie.spring.jdbc.exceptions.NotFoundException;

import java.util.List;

public interface CartoonService {
    int count();
    List<Cartoon> findAll();
    Cartoon findByCartoonId(int cartoon_id) throws NotFoundException;
    void delete(int cartoon_id) throws NotFoundException;
    void save(Cartoon cartoon) throws DataConflictException, MalformedDataException;

}