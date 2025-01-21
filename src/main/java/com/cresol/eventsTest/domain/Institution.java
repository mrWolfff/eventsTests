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

    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
    private List<Event> events;

    public Institution(InstitutionDTO dto){
        this.name = dto.name();
        this.type = dto.type();
    }
    public Institution(){
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeInstitution getType() {
        return type;
    }

    public void setType(TypeInstitution type) {
        this.type = type;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Long getId() {
        return id;
    }

}
