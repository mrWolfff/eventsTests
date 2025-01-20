package com.cresol.eventsTest.service;

import com.cresol.eventsTest.domain.Event;
import com.cresol.eventsTest.domain.EventDTO;
import com.cresol.eventsTest.domain.Institution;
import com.cresol.eventsTest.repository.EventRepository;
import com.cresol.eventsTest.repository.InstitutionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private EventPublisher eventoPublisher;

    public EventService(EventPublisher eventoPublisher) {
        this.eventoPublisher = eventoPublisher;
    }

    public List<EventDTO> getEvents() {
        return repository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, EventDTO.class))
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(event, EventDTO.class);
    }

    public EventDTO createEvent(EventDTO dto) {
        Event event = modelMapper.map(dto, Event.class);
        Event salvedEvent = repository.save(event);
        eventoPublisher.sendDelayedMessage("Ativando evento: " + salvedEvent.getId(), salvedEvent.getInitialDate());
        eventoPublisher.sendDelayedMessage("Desativando evento: " + salvedEvent.getId(), salvedEvent.getFinalDate());
        return modelMapper.map(salvedEvent, EventDTO.class);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}
