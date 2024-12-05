package com.example.Lab5.daos;

import com.example.Lab5.dtos.PetNameBreedDTO;
import com.example.Lab5.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.id = :id")
    int deleteById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE LOWER(p.name) = LOWER(:name)")
    int deletePetByName(String name);

    @Query("SELECT p FROM Pet p WHERE LOWER(p.animalType) = LOWER(:animalType)")
    List<Pet> findPetsByAnimalType(String animalType);

    @Query("SELECT p FROM Pet p WHERE LOWER(p.breed) = LOWER(:breed)")
    List<Pet> findPetsByBreed(String breed);

    @Query("SELECT AVG(p.age) FROM Pet p")
    Double findAverageAge();

    @Query("SELECT MAX(p.age) FROM Pet p")
    Integer findOldestAge();

    @Query("SELECT COUNT(p) FROM Pet p")
    long count();

    @Query("SELECT p FROM Pet p WHERE p.household.eircode = :eircode")
    List<Pet> findPetsByHousehold_Eircode(String eircode);

    @Query("SELECT p FROM Pet p WHERE p.household IS NULL")
    List<Pet> findByHouseholdIsNull();

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.household.eircode = :eircode")
    int deletePetsByHouseholdEircode(String eircode);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE LOWER(p.name) = LOWER(:name)")
    void deleteByNameIgnoreCase(String name);

    @Query("SELECT new com.example.Lab5.dtos.PetNameBreedDTO(p.name, p.animalType, p.breed) FROM Pet p")
    List<PetNameBreedDTO> findAllNameAndBreed();
}
