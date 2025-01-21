package com.cresol.eventsTest.scheduler;

import com.cresol.eventsTest.domain.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class EventsScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
        scheduleActivation(event);
        scheduleDeactivation(event);
    }

    private void scheduleActivation(Event event) {
        long delay = calculateDelayInSeconds(event.getInitialDate());
        if (delay > 0) {
            scheduler.schedule(() -> {
                event.setActive(true);
                System.out.println("Evento ativado: " + event);
            }, delay, TimeUnit.SECONDS);
        }
    }

    private void scheduleDeactivation(Event event) {
        long delay = calculateDelayInSeconds(event.getFinalDate());
        if (delay > 0) {
            scheduler.schedule(() -> {
                event.setActive(false);
                System.out.println("Evento desativado: " + event);
            }, delay, TimeUnit.SECONDS);
        }
    }

    private long calculateDelayInSeconds(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return Math.max(0, dateTime.atZone(ZoneId.systemDefault()).toEpochSecond()
                - now.atZone(ZoneId.systemDefault()).toEpochSecond());
    }
}
