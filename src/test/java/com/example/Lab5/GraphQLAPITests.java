package com.example.Lab5;


import com.example.Lab5.controllers.GraphQLAPI;
import com.example.Lab5.dtos.DeletionResponse;
import com.example.Lab5.dtos.HouseholdStatistics;
import com.example.Lab5.dtos.NewHousehold;
import com.example.Lab5.entities.Household;
import com.example.Lab5.entities.Pet;
import com.example.Lab5.services.HouseholdService;
import com.example.Lab5.services.PetService;
import com.example.Lab5.services.exceptions.BadDataException;
import com.example.Lab5.services.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GraphQLAPITests {

    @Mock
    private HouseholdService householdService;

    @Mock
    private PetService petService;

    @InjectMocks
    private GraphQLAPI graphQLAPI;

    private Household testHousehold;
    private Pet testPet;
    private NewHousehold newHousehold;

    @BeforeEach
    void setUp() {
        testHousehold = new Household();
        testHousehold.setEircode("D04X4H2");
        testHousehold.setNumberOfOccupants(3);
        testHousehold.setMaxNumberOfOccupants(4);
        testHousehold.setOwnerOccupied(true);

        testPet = new Pet();
        testPet.setId(1L);
        testPet.setName("Max");
        testPet.setAnimalType("Dog");
        testPet.setBreed("Labrador");

        newHousehold = new NewHousehold();
        newHousehold.setEircode("D04X4H2");
        newHousehold.setNumberOfOccupants(3);
        newHousehold.setMaxNumberOfOccupants(4);
        newHousehold.setOwnerOccupied(true);
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllHouseholds_ReturnsListOfHouseholds() {
        when(householdService.findAllHouseholds()).thenReturn(Arrays.asList(testHousehold));

        List<Household> result = graphQLAPI.getAllHouseholds();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getHouseholdByEircode_ExistingEircode_ReturnsHousehold() throws NotFoundException {
        when(householdService.findHouseholdByEircode("D04X4H2")).thenReturn(Optional.of(testHousehold));

        Household result = graphQLAPI.getHouseholdByEircode("D04X4H2");

        assertNotNull(result);
        assertEquals("D04X4H2", result.getEircode());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getHouseholdByEircode_NonexistentEircode_ThrowsNotFoundException() {
        when(householdService.findHouseholdByEircode("NONEXISTENT"))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                graphQLAPI.getHouseholdByEircode("NONEXISTENT"));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void createHousehold_ValidInput_ReturnsHousehold() throws BadDataException {
        when(householdService.createHousehold(any(Household.class))).thenReturn(testHousehold);

        Household result = graphQLAPI.createHousehold(newHousehold);

        assertNotNull(result);
        assertEquals(testHousehold.getEircode(), result.getEircode());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deletePetById_ExistingId_ReturnsSuccessResponse() throws NotFoundException {
        doNothing().when(petService).deletePetById(1);

        DeletionResponse result = graphQLAPI.deletePetById(1L);

        assertTrue(result.isSuccess());
        assertEquals("Pet successfully deleted", result.getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllPetsByAnimalType_ExistingType_ReturnsList() throws BadDataException {
        when(petService.findPetsByAnimalType("Dog")).thenReturn(Arrays.asList(testPet));

        List<Pet> result = graphQLAPI.getAllPetsByAnimalType("Dog");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
