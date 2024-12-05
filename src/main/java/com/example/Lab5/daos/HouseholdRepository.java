package com.example.Lab5.daos;

import com.example.Lab5.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {

    @Query("SELECT h FROM Household h WHERE h.eircode = :eircode")
    Optional<Household> findByEircode(String eircode);

    @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
    List<Household> findByPetsIsNull();

    @Query("SELECT h FROM Household h WHERE h.ownerOccupied = true")
    List<Household> findByOwnerOccupiedTrue();

    @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = 0")
    long countEmptyHouses();

    @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = h.maxNumberOfOccupants")
    long countFullHouses();
}
