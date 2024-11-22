package com.example.lab5.services;

import com.example.lab5.entities.Pet;
import com.example.lab5.exceptions.NotFoundException;
import com.example.lab5.repositories.PetRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet createPet(Pet pet) {
        pet.setId(null);
        return petRepository.save(pet);
    }

    @Override
    public Pet getPetById(Long id) throws NotFoundException {
        return petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet not found with id " + id));
    }

    @Override
    public Pet updatePet(Long id, Pet pet) throws NotFoundException {
        Pet existingPet = getPetById(id);
        existingPet.setName(pet.getName());
        existingPet.setAnimalType(pet.getAnimalType());
        existingPet.setBreed(pet.getBreed());
        existingPet.setAge(pet.getAge());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePetsByName(String name) {
        petRepository.deleteByNameIgnoreCase(name);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalTypeIgnoreCase(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAgeAsc(breed);
    }

    @Override
    public Double getAverageAge() {
        return petRepository.getAverageAge();
    }

    @Override
    public Integer getOldestAge() {
        return petRepository.getOldestAge();
    }

    @Override
    public Pet updatePetName(Long id, String name) {
        Pet pet = getPetById(id);
        pet.setName(name);
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Long id) throws NotFoundException {
        if (!petRepository.existsById(id)) {
            throw new NotFoundException("Cannot delete. Team not found with ID: " + id);
        }
    }
}