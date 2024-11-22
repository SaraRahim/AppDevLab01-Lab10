package com.example.lab5.services;

import com.example.lab5.entities.Household;
import java.util.List;

public interface HouseholdService {
    List<Household> getAllHouseholds();
    Household createHousehold(Household household);
    void deleteHouseholdById(String eircode);
    Household findByEircodeWithPets(String eircode);
    List<Household> findHouseholdsWithNoPets();
    Household findByEircode(String eircode);

}