package com.example.lab5;

import com.example.lab5.entities.Household;
import com.example.lab5.exceptions.NotFoundException;
import com.example.lab5.repositories.HouseholdRepository;
import com.example.lab5.services.HouseholdServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseholdServiceTests {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEircode() {
        // Arrange
        String eircode = "D02XY45";
        Household household = new Household();
        household.setEircode(eircode);
        household.setNumberOfOccupants(3);

        when(householdRepository.findById(eircode)).thenReturn(Optional.of(household));

        // Act
        Household foundHousehold = householdService.findByEircode(eircode);

        // Assert
        assertNotNull(foundHousehold, "Household should not be null");
        assertEquals(eircode, foundHousehold.getEircode(), "Eircode should match");
        verify(householdRepository, times(1)).findById(eircode);
    }

    @Test
    void testFindByEircodeNotFound() {
        // Arrange
        String eircode = "D02XY45";
        when(householdRepository.findById(eircode)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> householdService.findByEircode(eircode),
                "Should throw NotFoundException");
        verify(householdRepository, times(1)).findById(eircode);
    }

    @Test
    void testGetAllHouseholds() {
        // Arrange
        Household household = new Household();
        household.setEircode("D02XY45");
        household.setNumberOfOccupants(3);

        when(householdRepository.findAll()).thenReturn(List.of(household));

        // Act
        List<Household> households = householdService.getAllHouseholds();

        // Assert
        assertNotNull(households, "Households list should not be null");
        assertEquals(1, households.size(), "Should return one household");
        verify(householdRepository, times(1)).findAll();
    }

    @Test
    void testCreateHousehold() {
        // Arrange
        Household household = new Household();
        household.setEircode("D02XY45");
        household.setNumberOfOccupants(3);

        when(householdRepository.save(household)).thenReturn(household);

        // Act
        Household createdHousehold = householdService.createHousehold(household);

        // Assert
        assertNotNull(createdHousehold, "Created household should not be null");
        assertEquals("D02XY45", createdHousehold.getEircode(), "Eircode should match");
        verify(householdRepository, times(1)).save(household);
    }

    @Test
    void testDeleteHouseholdById() {
        // Arrange
        String eircode = "D02XY45";

        when(householdRepository.existsById(eircode)).thenReturn(true);

        // Act
        householdService.deleteHouseholdById(eircode);

        // Assert
        verify(householdRepository, times(1)).existsById(eircode);
        verify(householdRepository, times(1)).deleteById(eircode);
    }

    @Test
    void testFindHouseholdsWithNoPets() {
        // Arrange
        Household household = new Household();
        household.setEircode("D02XY45");
        household.setNumberOfOccupants(3);

        when(householdRepository.findHouseholdsWithNoPets()).thenReturn(List.of(household));

        // Act
        List<Household> households = householdService.findHouseholdsWithNoPets();

        // Assert
        assertNotNull(households, "Households list should not be null");
        assertEquals(1, households.size(), "Should return one household with no pets");
        verify(householdRepository, times(1)).findHouseholdsWithNoPets();
    }
}