package com.cresol.eventsTest.controller;

import com.cresol.eventsTest.domain.EventDTO;
import com.cresol.eventsTest.domain.Institution;
import com.cresol.eventsTest.repository.EventRepository;
import com.cresol.eventsTest.repository.InstitutionRepository;
import com.cresol.eventsTest.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService service;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private EventRepository repository;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents() {
        return ResponseEntity.ok(service.getEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable @NotNull Long id) {
        EventDTO dto = service.getEventById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody @Valid EventDTO dto, UriComponentsBuilder uriBuilder) {
        Institution institution = institutionRepository.findById(dto.institution().getId())
                .orElseThrow();
        boolean isExistEvent = repository.findExistingEvent(
                institution.getId(), dto.initialDate(), dto.finalDate()).isPresent();
        if (isExistEvent) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Um evento já existe para esta instituição no intervalo especificado.");
        }
        EventDTO event = service.createEvent(dto);
        URI address = uriBuilder.path("/event/{id}").buildAndExpand(event.id()).toUri();
        return ResponseEntity.created(address).body(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable @NotNull Long id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
