package com.example.Lab5.services;

import com.example.Lab5.daos.PetRepository;
import com.example.Lab5.dtos.PetNameBreedDTO;
import com.example.Lab5.entities.Pet;
import com.example.Lab5.services.exceptions.BadDataException;
import com.example.Lab5.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Transactional
    @Override
    public Pet createPet(Pet pet) throws BadDataException {
        if (petRepository.existsById(Math.toIntExact(pet.getId()))) {
            throw new BadDataException("Pet already exists");
        }
        validatePet(pet);
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(int id) throws NotFoundException {
        return petRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Pet not found ID: " + id));
    }

    @Transactional
    @Override
    public Pet updatePet(Pet pet) throws BadDataException, NotFoundException {
        Pet existingPet = petRepository.findById(Math.toIntExact(pet.getId())).orElseThrow(
                () -> new NotFoundException("Pet not found ID: " + pet.getId()));
        validatePet(pet);
        existingPet.setName(pet.getName());
        existingPet.setBreed(pet.getBreed());
        existingPet.setAnimalType(pet.getAnimalType());
        existingPet.setAge(pet.getAge());
        return petRepository.save(existingPet);
    }

    @Transactional
    @Override
    public void deletePetById(int id) throws NotFoundException {
        if (!petRepository.existsById(id)) {
            throw new NotFoundException("Pet not found ID: " + id);
        }
        petRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deletePetByName(String name) throws NotFoundException {
        int rowsAffected = petRepository.deletePetByName(name);
        if (rowsAffected != 1) {
            throw new NotFoundException("Pet not found with name: " + name);
        }
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) throws BadDataException {
        List<Pet> pets = petRepository.findPetsByAnimalType(animalType);
        if (pets.isEmpty()) {
            throw new BadDataException("No pets found for animal type: " + animalType);
        }
        return pets;
    }

    @Override
    public List<Pet> getPetsByBreed(String breed) throws BadDataException {
        List<Pet> pets = petRepository.findPetsByBreed(breed);
        if (pets.isEmpty()) {
            throw new BadDataException("No pets found for breed: " + breed);
        }
        return pets;
    }

    @Override
    public PetStatistics getPetStatistics() {
        Double averageAge = petRepository.findAverageAge();
        Integer oldestAge = petRepository.findOldestAge();
        long totalCount = petRepository.count();
        return new PetStatistics(
                averageAge != null ? averageAge : 0.0,
                oldestAge != null ? oldestAge : 0,
                totalCount
        );
    }

    @Override
    public List<PetNameBreedDTO> getPetNamesAndBreeds() {
        return petRepository.findAllNameAndBreed();
    }

    private void validatePet(Pet pet) throws BadDataException {
        if (pet.getName() == null || pet.getName().isBlank()) {
            throw new BadDataException("Pet name cannot be empty");
        }
        if (pet.getBreed() == null || pet.getBreed().isBlank()) {
            throw new BadDataException("Pet breed cannot be empty");
        }
        if (pet.getAnimalType() == null || pet.getAnimalType().isBlank()) {
            throw new BadDataException("Pet animal type cannot be empty");
        }
        if (pet.getAge() < 0 || pet.getAge() > 50) {
            throw new BadDataException("Pet age must be between 0 and 50");
        }
    }
}
