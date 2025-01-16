package com.cresol.eventsTest.controller;

import com.cresol.eventsTest.domain.EventDTO;
import com.cresol.eventsTest.domain.InstitutionDTO;
import com.cresol.eventsTest.service.EventService;
import com.cresol.eventsTest.service.InstitutionService;
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
@RequestMapping("/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService service;

    @GetMapping
    public ResponseEntity<List<InstitutionDTO>> getInstitutions() {
        return ResponseEntity.ok(service.getInstitutions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDTO> getInstitutionById(@PathVariable @NotNull Long id) {
        InstitutionDTO dto = service.getInstitutionById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<InstitutionDTO> createInstitution(@RequestBody @Valid InstitutionDTO dto, UriComponentsBuilder uriBuilder) {
        InstitutionDTO institution = service.createInstitution(dto);
        URI address = uriBuilder.path("/event/{id}").buildAndExpand(institution.id()).toUri();
        return ResponseEntity.created(address).body(institution);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InstitutionDTO> deleteEvent(@PathVariable @NotNull Long id) {
        service.deleteInstitution(id);
        return ResponseEntity.noContent().build();
    }
}
