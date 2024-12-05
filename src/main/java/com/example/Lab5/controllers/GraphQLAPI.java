package com.example.Lab5.controllers;

import com.example.Lab5.dtos.DeletionResponse;
import com.example.Lab5.dtos.HouseholdStatistics;
import com.example.Lab5.dtos.NewHousehold;
import com.example.Lab5.entities.Household;
import com.example.Lab5.entities.Pet;
import com.example.Lab5.services.HouseholdService;
import com.example.Lab5.services.PetService;
import com.example.Lab5.services.exceptions.BadDataException;
import com.example.Lab5.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLAPI {
    private final HouseholdService householdService;
    private final PetService petService;

    // Query to get all households
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @QueryMapping(name = "getAllHouseholds")
    public List<Household> getAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    // Query to get a household by Eircode
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @QueryMapping(name = "getHouseholdByEircode")
    public Household getHouseholdByEircode(@Argument String eircode) throws NotFoundException {
        return householdService.findHouseholdByEircode(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found with eircode: " + eircode));
    }

    // Query to get household statistics
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @QueryMapping(name = "getHouseholdStatistics")
    public HouseholdStatistics getHouseholdStatistics() {
        return householdService.getHouseholdStatistics();
    }

    // Mutation to create a household
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @MutationMapping(name = "createHousehold")
    public Household createHousehold(@NotNull @Valid @Argument("input") NewHousehold newHousehold) throws BadDataException {
        Household household = new Household(newHousehold.getEircode(), newHousehold.getNumberOfOccupants(),
                newHousehold.getMaxNumberOfOccupants(), null, newHousehold.isOwnerOccupied());
        return householdService.createHousehold(household);
    }

    // Mutation to delete a household by Eircode
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @MutationMapping(name = "deleteHouseholdByEircode")
    public DeletionResponse deleteHouseholdByEircode(@Argument String eircode) throws NotFoundException {
        householdService.deleteHouseholdByEircode(eircode);
        return new DeletionResponse(true, "Household successfully deleted");
    }

    // Query to find pets by animal type
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @QueryMapping(name = "getAllPetsByAnimalType")
    public List<Pet> getAllPetsByAnimalType(@Argument String animalType) throws BadDataException {
        return petService.findPetsByAnimalType(animalType);
    }

    // Mutation to delete a pet by ID
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @MutationMapping(name = "deletePetById")
    public DeletionResponse deletePetById(@Argument Long id) throws NotFoundException {
        petService.deletePetById(id.intValue());
        return new DeletionResponse(true, "Pet successfully deleted");
    }
}
