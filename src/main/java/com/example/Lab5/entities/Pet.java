package com.example.Lab5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "animal_type", nullable = false)
    private String animalType;

    @Column(name = "breed", nullable = false)
    private String breed;

    @ManyToOne
    @JoinColumn(name = "household_eircode", referencedColumnName = "eircode")
    @JsonIgnore
    private Household household;

    public Pet(String buddy, String dog, String goldenRetriever, int i) {
    }
}
