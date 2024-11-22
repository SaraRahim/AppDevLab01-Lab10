package com.example.lab5.services;

import com.example.lab5.entities.Household;
import com.example.lab5.exceptions.NotFoundException;
import com.example.lab5.repositories.HouseholdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    @Override
    public Household findByEircode(String eircode) {
        return householdRepository.findById(eircode).orElseThrow(
                () -> new NotFoundException("Household not found with eircode " + eircode));
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public void deleteHouseholdById(String eircode) {
        if (!householdRepository.existsById(eircode)) {
            throw new NotFoundException("Household not found with eircode " + eircode);
        }
        householdRepository.deleteById(eircode);
    }

    @Override
    public Household findByEircodeWithPets(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }


}