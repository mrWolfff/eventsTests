package com.cresol.eventsTest.service;

import com.cresol.eventsTest.domain.Event;
import com.cresol.eventsTest.domain.EventDTO;
import com.cresol.eventsTest.repository.EventRepository;
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
        repository.save(event);
        return modelMapper.map(event, EventDTO.class);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}
