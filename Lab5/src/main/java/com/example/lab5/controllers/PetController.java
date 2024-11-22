package com.example.lab5.controllers;

import com.example.lab5.entities.Pet;
import com.example.lab5.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        return petService.updatePet(id, pet);
    }

    @DeleteMapping("/{id}")
    public void deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
    }

    @DeleteMapping("/by-name/{name}")
    public void deletePetsByName(@PathVariable String name) {
        petService.deletePetsByName(name);
    }

    @GetMapping("/by-type/{animal_type}")
    public List<Pet> findPetsByAnimalType(@PathVariable String animal_type) {
        return petService.findPetsByAnimalType(animal_type);
    }

    @GetMapping("/by-breed/{breed}")
    public List<Pet> findPetsByBreed(@PathVariable String breed) {
        return petService.findPetsByBreed(breed);
    }

    @GetMapping("/average-age")
    public Double getAverageAge() {
        return petService.getAverageAge();
    }

    @GetMapping("/oldest-age")
    public Integer getOldestAge() {
        return petService.getOldestAge();
    }

    @PatchMapping("/{id}/name")
    public Pet updatePetName(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String newName = requestBody.get("name");
        return petService.updatePetName(id, newName);
    }
}