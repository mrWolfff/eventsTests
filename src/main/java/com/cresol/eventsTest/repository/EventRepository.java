package com.cresol.eventsTest.repository;

import com.cresol.eventsTest.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
