package com.example.lab5;

import com.example.lab5.entities.Household;
import com.example.lab5.entities.Pet;
import com.example.lab5.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    public void testFindByAnimalType() {
        var pets = petRepository.findByAnimalTypeIgnoreCase("Dog");
        assertEquals(3, pets.size(), "Should find 2 dogs");
    }

    @Test
    public void testPetCreationUsingSetters() {
        Household household = new Household();
        household.setEircode("D02XY45");
        household.setNumberOfOccupants(3);
        household.setMaxNumberOfOccupants(5);
        household.setOwnerOccupied(true);

        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setAnimalType("Dog");
        pet.setBreed("Golden Retriever");
        pet.setAge(3);
        pet.setHousehold(household);

        assertNotNull(pet);
        assertEquals("Buddy", pet.getName());
        assertEquals("Dog", pet.getAnimalType());
        assertEquals("Golden Retriever", pet.getBreed());
        assertEquals(3, pet.getAge());
        assertEquals("D02XY45", pet.getHousehold().getEircode());
    }

    @Test
    public void testGetAverageAge() {
        Double averageAge = petRepository.getAverageAge();
        assertNotNull(averageAge);
    }
}