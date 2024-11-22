package com.example.lab5;

import com.example.lab5.entities.Household;
import com.example.lab5.repositories.HouseholdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class HouseholdRepositoryTest {

    @Autowired
    private HouseholdRepository householdRepository;

    @Test
    public void testFindByEircode() {
        Household household = new Household();
        household.setEircode("D02XY45");
        household.setNumberOfOccupants(3);
        householdRepository.save(household);
        Optional<Household> retrievedHousehold = Optional.ofNullable(householdRepository.findByEircode("D02XY45"));

        assertTrue(retrievedHousehold.isPresent(), "Household should be found by eircode");
        assertEquals("D02XY45", retrievedHousehold.get().getEircode(), "Eircode should match");
    }

    @Test
    public void testSaveHousehold() {
        Household household = new Household();
        household.setEircode("D02XY45");
        household.setNumberOfOccupants(4);
        Household savedHousehold = householdRepository.save(household);

        assertNotNull(savedHousehold.getEircode(), "Saved household should have an eircode");
        assertEquals("D02XY45", savedHousehold.getEircode());
    }
}