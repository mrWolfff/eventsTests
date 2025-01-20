package com.cresol.eventsTest.service;

import com.cresol.eventsTest.configuration.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendDelayedMessage(String messageStr, LocalDateTime date) {
        long delay = Duration.between(LocalDateTime.now(), date).toMillis();

        MessageProperties props = new MessageProperties();
        props.setHeader("x-delay", delay);
        Message message = new Message(messageStr.getBytes(StandardCharsets.UTF_8), props);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "", message);
    }
}
