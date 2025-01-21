package com.cresol.eventsTest.repository;

import com.cresol.eventsTest.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.institution.id = :institutionId " +
            "AND e.initialDate <= :finalDate AND e.finalDate >= :initialDate")
    Optional<Event> findExistingEvent(@Param("institutionId") Long id,
                                      @Param("initialDate") LocalDateTime initialDate,
                                      @Param("finalDate") LocalDateTime finalDate);
}
