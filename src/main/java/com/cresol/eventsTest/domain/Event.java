package com.cresol.eventsTest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public boolean isACtive() {
        LocalDate today = LocalDate.now();
        return (today.isEqual(initialDate) || today.isAfter(initialDate)) && today.isBefore(finalDate.plusDays(1));
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
