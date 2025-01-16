package com.cresol.eventsTest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Date initialDate;
    @NotNull
    private Date finalDate;
    @NotNull
    private Boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

}
