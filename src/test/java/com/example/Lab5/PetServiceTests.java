package com.example.Lab5;

import com.example.Lab5.entities.Pet;
import com.example.Lab5.services.PetServiceImpl;
import com.example.Lab5.services.exceptions.BadDataException;
import com.example.Lab5.services.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import com.example.Lab5.daos.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private Pet validPet;
    private Pet invalidPet;

    @BeforeEach
    void setUp() {
        validPet = new Pet();
        validPet.setId(1L);
        validPet.setName("Max");
        validPet.setBreed("Labrador");
        validPet.setAnimalType("Dog");
        validPet.setAge(5);

        invalidPet = new Pet();
        invalidPet.setId(2L);
        invalidPet.setName("");
        invalidPet.setBreed("");
        invalidPet.setAnimalType("");
        invalidPet.setAge(-1);
    }

    @Test
    void createPet_ValidPet_Success() throws BadDataException {
        when(petRepository.existsById(1)).thenReturn(false);
        when(petRepository.save(any(Pet.class))).thenReturn(validPet);

        Pet result = petService.createPet(validPet);

        assertNotNull(result);
        assertEquals(validPet.getName(), result.getName());
        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void createPet_ExistingPet_ThrowsBadDataException() {
        when(petRepository.existsById(1)).thenReturn(true);

        assertThrows(BadDataException.class, () -> petService.createPet(validPet));
        verify(petRepository, never()).save(any(Pet.class));
    }

    @Test
    void createPet_InvalidPet_ThrowsBadDataException() {
        assertThrows(BadDataException.class, () -> petService.createPet(invalidPet));
        verify(petRepository, never()).save(any(Pet.class));
    }

    @Test
    void getPetById_ExistingId_ReturnsPet() throws NotFoundException {
        when(petRepository.findById(1)).thenReturn(Optional.of(validPet));

        Pet result = petService.getPetById(1);

        assertNotNull(result);
        assertEquals(validPet.getName(), result.getName());
    }

    @Test
    void getPetById_NonexistentId_ThrowsNotFoundException() {
        when(petRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> petService.getPetById(999));
    }

    @Test
    void deletePetById_ExistingId_Success() throws NotFoundException {
        when(petRepository.existsById(1)).thenReturn(true);

        petService.deletePetById(1);

        verify(petRepository).deleteById(1);
    }

    @Test
    void deletePetById_NonexistentId_ThrowsNotFoundException() {
        when(petRepository.existsById(999)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> petService.deletePetById(999));
        verify(petRepository, never()).deleteById(999);
    }


    @Test
    void findPetsByAnimalType_ExistingType_ReturnsList() throws BadDataException {
        List<Pet> pets = Arrays.asList(validPet);
        when(petRepository.findPetsByAnimalType("Dog")).thenReturn(pets);

        List<Pet> result = petService.findPetsByAnimalType("Dog");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void findPetsByAnimalType_NonexistentType_ThrowsBadDataException() {
        when(petRepository.findPetsByAnimalType("NonexistentType")).thenReturn(List.of());

        assertThrows(BadDataException.class, () -> petService.findPetsByAnimalType("NonexistentType"));
    }
}