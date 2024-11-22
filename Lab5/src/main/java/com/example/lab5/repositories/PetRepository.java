package com.example.lab5.repositories;

import com.example.lab5.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByAnimalTypeIgnoreCase(String animalType);

    List<Pet> findByBreedOrderByAgeAsc(String breed);

    @Modifying
    @Query("DELETE FROM Pet p WHERE LOWER(p.name) = LOWER(:name)")
    void deleteByNameIgnoreCase(String name);

    @Query("SELECT AVG(p.age) FROM Pet p")
    Double getAverageAge();

    @Query("SELECT MAX(p.age) FROM Pet p")
    Integer getOldestAge();
}