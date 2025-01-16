package com.cresol.eventsTest.controller;

import com.cresol.eventsTest.domain.EventDTO;
import com.cresol.eventsTest.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService service;

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
    public ResponseEntity<EventDTO> createEvent(@RequestBody @Valid EventDTO dto, UriComponentsBuilder uriBuilder) {
        EventDTO event = service.createEvent(dto);
        URI address = uriBuilder.path("/event/{id}").buildAndExpand(event.id()).toUri();
        return ResponseEntity.created(address).body(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDTO> deleteInstitution(@PathVariable @NotNull Long id) {
        service.deleteInstitution(id);
        return ResponseEntity.noContent().build();
    }
}
