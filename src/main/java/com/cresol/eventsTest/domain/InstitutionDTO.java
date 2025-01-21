package com.cresol.eventsTest.domain;

public record InstitutionDTO(Long id,  String name, TypeInstitution type) {
    public InstitutionDTO(Institution institution) {
        this(institution.getId(), institution.getName(), institution.getType());
    }

    public static InstitutionDTO fromEntity(Institution institution) {
        return new InstitutionDTO(institution.getId(), institution.getName(), institution.getType());
    }
}
