package com.cresol.eventsTest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private LocalDateTime initialDate;
    @NotNull
    private LocalDateTime finalDate;
    @NotNull
    private Boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    public Event(EventDTO dto) {
        this.name = dto.name();
        this.initialDate = dto.initialDate();
        this.finalDate = dto.finalDate();
        this.active = dto.active();
    }
    public Event(){
    }

    public boolean isActive() {
        LocalDateTime today = LocalDateTime.now();
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

    public void setActive(Boolean active) {
        this.active = active;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                ", active=" + active +
                ", institution=" + institution +
                '}';
    }
}
