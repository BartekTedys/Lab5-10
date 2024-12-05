package com.example.Lab5;


import com.example.Lab5.daos.HouseholdRepository;
import com.example.Lab5.dtos.HouseholdStatistics;
import com.example.Lab5.entities.Household;
import com.example.Lab5.services.HouseholdServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseholdServiceTests {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    private Household validHousehold;

    @BeforeEach
    void setUp() {
        validHousehold = new Household();
        validHousehold.setEircode("D04X4H2");
        validHousehold.setNumberOfOccupants(3);
        validHousehold.setMaxNumberOfOccupants(4);
        validHousehold.setOwnerOccupied(true);
    }

    @Test
    void createHousehold_Success() {
        when(householdRepository.save(any(Household.class))).thenReturn(validHousehold);

        Household result = householdService.createHousehold(validHousehold);

        assertNotNull(result);
        assertEquals(validHousehold.getEircode(), result.getEircode());
        verify(householdRepository).save(any(Household.class));
    }

    @Test
    void findAllHouseholds_ReturnsListOfHouseholds() {
        List<Household> households = Arrays.asList(validHousehold);
        when(householdRepository.findAll()).thenReturn(households);

        List<Household> result = householdService.findAllHouseholds();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void findHouseholdByEircode_ExistingEircode_ReturnsHousehold() {
        when(householdRepository.findByEircode("D04X4H2")).thenReturn(Optional.of(validHousehold));

        Optional<Household> result = householdService.findHouseholdByEircode("D04X4H2");

        assertTrue(result.isPresent());
        assertEquals(validHousehold.getEircode(), result.get().getEircode());
    }

    @Test
    void findHouseholdByEircode_NonexistentEircode_ReturnsEmpty() {
        when(householdRepository.findByEircode("NONEXISTENT")).thenReturn(Optional.empty());

        Optional<Household> result = householdService.findHouseholdByEircode("NONEXISTENT");

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteHouseholdByEircode_Success() {
        householdService.deleteHouseholdByEircode("D04X4H2");

        verify(householdRepository).deleteById("D04X4H2");
    }

    @Test
    void findHouseholdsWithNoPets_ReturnsListOfHouseholds() {
        List<Household> households = Arrays.asList(validHousehold);
        when(householdRepository.findByPetsIsNull()).thenReturn(households);

        List<Household> result = householdService.findHouseholdsWithNoPets();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void findHouseholdsWithOwnerOccupied_ReturnsListOfHouseholds() {
        List<Household> households = Arrays.asList(validHousehold);
        when(householdRepository.findByOwnerOccupiedTrue()).thenReturn(households);

        List<Household> result = householdService.findHouseholdsWithOwnerOccupied();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void updateHousehold_ExistingHousehold_Success() {
        Household updatedHousehold = new Household();
        updatedHousehold.setEircode("D04X4H2");
        updatedHousehold.setNumberOfOccupants(4);
        updatedHousehold.setMaxNumberOfOccupants(5);
        updatedHousehold.setOwnerOccupied(false);

        when(householdRepository.findByEircode("D04X4H2")).thenReturn(Optional.of(validHousehold));
        when(householdRepository.save(any(Household.class))).thenReturn(updatedHousehold);

        Household result = householdService.updateHousehold("D04X4H2", updatedHousehold);

        assertNotNull(result);
        assertEquals(updatedHousehold.getNumberOfOccupants(), result.getNumberOfOccupants());
        assertEquals(updatedHousehold.isOwnerOccupied(), result.isOwnerOccupied());
    }

    @Test
    void updateHousehold_NonexistentHousehold_ReturnsNull() {
        when(householdRepository.findByEircode("NONEXISTENT")).thenReturn(Optional.empty());

        Household result = householdService.updateHousehold("NONEXISTENT", validHousehold);

        assertNull(result);
        verify(householdRepository, never()).save(any(Household.class));
    }

}