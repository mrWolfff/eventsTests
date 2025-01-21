package com.cresol.eventsTest.service;

import com.cresol.eventsTest.domain.Event;
import com.cresol.eventsTest.domain.EventDTO;
import com.cresol.eventsTest.domain.Institution;
import com.cresol.eventsTest.repository.EventRepository;
import com.cresol.eventsTest.scheduler.EventsScheduler;
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
    private EventsScheduler scheduler;


    public List<EventDTO> getEvents() {
        return repository.findAll()
                .stream()
                .map(EventDTO::new)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(event, EventDTO.class);
    }

    public EventDTO createEvent(EventDTO dto, Institution institution) {
        Event event = new Event(dto);
        event.setInstitution(institution);
        scheduler.addEvent(event);
        repository.save(event);
        return EventDTO.fromEntity(event);
    }

    public void deleteInstitution(Long id) {
        repository.deleteById(id);
    }

}
