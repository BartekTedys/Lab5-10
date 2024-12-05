package com.example.Lab5.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "household")
public class Household {

    @Id
    @Column(name = "eircode")
    private String eircode;

    @Column(name = "number_of_occupants")
    private int numberOfOccupants;

    @Column(name = "max_number_of_occupants")
    private int maxNumberOfOccupants;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Pet> pets;

    @Getter
    @Setter
    @Column(name = "owner_occupied", nullable = false)
    private boolean ownerOccupied;

}
