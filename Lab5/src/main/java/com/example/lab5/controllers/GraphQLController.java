package com.example.lab5.controllers;

import com.example.lab5.dto.Statistics;
import com.example.lab5.entities.Household;
import com.example.lab5.entities.Pet;
import com.example.lab5.dto.HouseholdDTO;
import com.example.lab5.exceptions.NotFoundException;
import com.example.lab5.services.HouseholdService;
import com.example.lab5.services.PetService;
import com.example.lab5.exceptions.BadDataException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLController {
    private HouseholdService householdService;
    private PetService petService;

    @QueryMapping
    public List<Household> findAllHouseholds() {return householdService.getAllHouseholds();}

    @QueryMapping
    public Household findHouseholdByEircode(@Argument String eircode) throws NotFoundException {
        return householdService.findByEircodeWithPets(eircode);
    }

    @QueryMapping
    public List<Pet> findPetsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Pet findPetById(@Argument Long id) throws NotFoundException {
        return petService.getPetById(id);
    }

    @QueryMapping
    public Statistics getStatistics() {
        Double averageAge = petService.getAverageAge();
        Integer oldestAge = petService.getOldestAge();
        return new Statistics(averageAge, oldestAge);
    }

    @MutationMapping
    public Household createHousehold(@Valid @Argument("household") HouseholdDTO householdDTO) throws BadDataException, NotFoundException {
        Household household = null;
        String eircode = householdDTO.eircode();
        Integer numberOfOccupants = householdDTO.numberOfOccupants();
        Boolean ownerOccupied = householdDTO.ownerOccupied();
        if (eircode != null && !eircode.isEmpty()) {
            household = new Household(eircode, numberOfOccupants, ownerOccupied);
        }
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public String deleteHousehold(@Argument String eircode) throws NotFoundException {
        householdService.deleteHouseholdById(eircode);
        return "household deleted";
    }

    @MutationMapping
    public String deletePet(@Argument Long id) throws NotFoundException {
        petService.deletePetById(id);
        return "pet deleted";
    }
}