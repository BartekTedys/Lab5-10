package com.example.Lab5.controllers;

import com.example.Lab5.dtos.HouseholdStatistics;
import com.example.Lab5.entities.Household;
import com.example.Lab5.services.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/households")
@RequiredArgsConstructor
@Validated
public class HouseholdController {

    private final HouseholdService householdService;

    @PostMapping
    public Household createHousehold(@Valid @RequestBody Household household) {
        return householdService.createHousehold(household);
    }

    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    @GetMapping("/{eircode}")
    public Optional<Household> getHouseholdByEircode(@PathVariable @NotBlank String eircode) {
        return householdService.findHouseholdByEircode(eircode);
    }

    @DeleteMapping("/{eircode}")
    public void deleteHousehold(@PathVariable @NotBlank String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
    }

    @GetMapping("/no-pets")
    public List<Household> getHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping("/owner-occupied")
    public List<Household> getHouseholdsWithOwnerOccupied() {
        return householdService.findHouseholdsWithOwnerOccupied();
    }

    @PutMapping("/{eircode}")
    public Household updateHousehold(
            @PathVariable @NotBlank String eircode,
            @Valid @RequestBody Household household) {
        return householdService.updateHousehold(eircode, household);
    }

    @GetMapping("/statistics")
    public HouseholdStatistics getHouseholdStatistics() {
        return householdService.getHouseholdStatistics();
    }
}