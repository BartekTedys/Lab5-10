package com.example.Lab5.services;

import com.example.Lab5.dtos.HouseholdStatistics;
import com.example.Lab5.entities.Household;

import java.util.List;
import java.util.Optional;

public interface HouseholdService {

    // Create a new household
    Household createHousehold(Household household);

    // Get all households
    List<Household> findAllHouseholds();

    // Get household by eircode
    Optional<Household> findHouseholdByEircode(String eircode);

    // Delete household by eircode
    void deleteHouseholdByEircode(String eircode);

    // Get households with no pets
    List<Household> findHouseholdsWithNoPets();

    // Get households that are owner-occupied
    List<Household> findHouseholdsWithOwnerOccupied();

    // Update household details
    Household updateHousehold(String eircode, Household household);

    // Get statistics for households
    HouseholdStatistics getHouseholdStatistics();
}
