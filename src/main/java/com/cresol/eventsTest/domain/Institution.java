package com.cresol.eventsTest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private TypeInstitution type;
    @NotNull
    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
    private List<Event> events;

}
