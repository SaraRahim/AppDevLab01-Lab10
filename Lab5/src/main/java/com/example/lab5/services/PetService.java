package com.example.lab5.services;

import com.example.lab5.entities.Pet;
import com.example.lab5.exceptions.NotFoundException;
import java.util.List;

public interface PetService {
    List<Pet> getAllPets();
    Pet createPet(Pet pet);
    Pet getPetById(Long id) throws NotFoundException;
    Pet updatePet(Long id, Pet pet) throws NotFoundException;
    void deletePetById(Long id) throws NotFoundException;
    void deletePetsByName(String name);
    List<Pet> findPetsByAnimalType(String animalType);
    List<Pet> findPetsByBreed(String breed);
    Double getAverageAge();
    Integer getOldestAge();
    Pet updatePetName(Long id, String name);
}