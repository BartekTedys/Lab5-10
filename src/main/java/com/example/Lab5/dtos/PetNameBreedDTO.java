package com.example.Lab5.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for representing specific fields of a Pet entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetNameBreedDTO {

    private String name;
    private String animalType;
    private String breed;
}
