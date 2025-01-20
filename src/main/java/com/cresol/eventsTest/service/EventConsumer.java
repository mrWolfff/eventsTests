package com.cresol.eventsTest.service;

import com.cresol.eventsTest.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EventConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void consumeMessage(String message) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "RABBITMQ MESSAGE: " + message);
    }
}
