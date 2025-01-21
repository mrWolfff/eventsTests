package com.cresol.eventsTest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record EventDTO(Long id,
                       String name,
                       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime initialDate,
                       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime finalDate,
                       Boolean active,
                       Long institutionId) {
    public EventDTO(Event event) {
        this(event.getId(), event.getName(), event.getInitialDate(), event.getFinalDate(), event.isActive(), event.getInstitution().getId());
    }
    public static EventDTO fromEntity(Event event) {
        return new EventDTO(event.getId(), event.getName(), event.getInitialDate(), event.getFinalDate(), event.isActive(), event.getInstitution().getId());
    }

}
