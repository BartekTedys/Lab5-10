package com.example.Lab5.controllers;

import com.example.Lab5.dtos.PetNameBreedDTO;
import com.example.Lab5.entities.Pet;
import com.example.Lab5.services.PetService;
import com.example.Lab5.services.PetStatistics;
import com.example.Lab5.services.exceptions.BadDataException;
import com.example.Lab5.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
@Validated
public class PetController {

    private final PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable("id") @Min(1) int id) throws NotFoundException {
        return petService.getPetById(id);
    }

    @GetMapping("/statistics")
    public ResponseEntity<PetStatistics> getPetStatistics() {
        PetStatistics statistics = petService.getPetStatistics();
        return ResponseEntity.ok(statistics);
    }

    @PostMapping
    public Pet createPet(@Valid @RequestBody Pet pet) throws BadDataException, NotFoundException {
        return petService.createPet(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePetById(@PathVariable("id") @Min(1) int id) throws NotFoundException {
        petService.deletePetById(id);
    }

    @GetMapping("/names-and-breeds")
    public List<PetNameBreedDTO> getPetNamesAndBreeds(
            @RequestParam(required = false) @Min(1) Integer id) throws NotFoundException {
        if (id != null) {
            Pet pet = petService.getPetById(id);
            PetNameBreedDTO petDTO = new PetNameBreedDTO(pet.getName(), pet.getAnimalType(), pet.getBreed());
            return List.of(petDTO);
        }
        return petService.getPetNamesAndBreeds();
    }

    @GetMapping("/by-animal-type/{animalType}")
    public List<Pet> getPetsByAnimalType(@PathVariable @Valid String animalType) throws BadDataException {
        return petService.findPetsByAnimalType(animalType);
    }
}