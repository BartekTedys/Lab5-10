package com.example.Lab5.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewHousehold {

    @NotBlank
    private String eircode;

    @NotNull
    private Integer numberOfOccupants;

    @NotNull
    private Integer maxNumberOfOccupants;

    private boolean ownerOccupied;

    // Getters and Setters

}
