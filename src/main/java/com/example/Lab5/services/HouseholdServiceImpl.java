package com.example.Lab5.services;

import com.example.Lab5.daos.HouseholdRepository;
import com.example.Lab5.dtos.HouseholdStatistics;
import com.example.Lab5.entities.Household;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> findAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Optional<Household> findHouseholdByEircode(String eircode) {
        return householdRepository.findByEircode(eircode);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) {
        householdRepository.deleteById(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findByPetsIsNull();
    }

    @Override
    public List<Household> findHouseholdsWithOwnerOccupied() {
        return householdRepository.findByOwnerOccupiedTrue();
    }

    @Override
    public Household updateHousehold(String eircode, Household household) {
        Optional<Household> existingHousehold = householdRepository.findByEircode(eircode);
        if (existingHousehold.isPresent()) {
            Household updatedHousehold = existingHousehold.get();
            updatedHousehold.setNumberOfOccupants(household.getNumberOfOccupants());
            updatedHousehold.setMaxNumberOfOccupants(household.getMaxNumberOfOccupants());
            updatedHousehold.setOwnerOccupied(household.isOwnerOccupied());
            return householdRepository.save(updatedHousehold);
        }
        return null;
    }

    @Override
    public HouseholdStatistics getHouseholdStatistics() {
        long emptyHouses = householdRepository.countEmptyHouses();
        long fullHouses = householdRepository.countFullHouses();
        return new HouseholdStatistics(emptyHouses, fullHouses);
    }


}
