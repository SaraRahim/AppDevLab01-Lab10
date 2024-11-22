package com.example.lab5;

import com.example.lab5.entities.Household;
import com.example.lab5.entities.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetServiceTest {

    @Test
    public void testPetCreation() {
        Household household = new Household();
        household.setEircode("D02XY45");
        household.setNumberOfOccupants(3);
        household.setMaxNumberOfOccupants(5);
        household.setOwnerOccupied(true);

        Pet pet = new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, household);

        assertNotNull(pet);
        assertEquals("Buddy", pet.getName());
        assertEquals("Dog", pet.getAnimalType());
        assertEquals("Golden Retriever", pet.getBreed());
        assertEquals(3, pet.getAge());
        assertEquals("D02XY45", pet.getHousehold().getEircode());
    }
}