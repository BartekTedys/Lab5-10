package com.example.Lab5.services;

import com.example.Lab5.dtos.PetNameBreedDTO;
import com.example.Lab5.entities.Pet;
import com.example.Lab5.services.exceptions.BadDataException;
import com.example.Lab5.services.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Pet createPet(Pet pet) throws NotFoundException, BadDataException;
    List<Pet> getAllPets();
    Pet getPetById(int id) throws NotFoundException;
    Pet updatePet(Pet pet) throws NotFoundException, BadDataException;
    void deletePetById(int id) throws NotFoundException;
    void deletePetByName(String petName) throws NotFoundException, BadDataException;
    List<Pet> findPetsByAnimalType(String animalType) throws BadDataException;
    List<Pet> getPetsByBreed(String breed) throws BadDataException;
    PetStatistics getPetStatistics();
    List<PetNameBreedDTO> getPetNamesAndBreeds();

}
