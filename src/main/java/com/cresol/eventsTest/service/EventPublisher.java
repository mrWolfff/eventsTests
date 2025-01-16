package com.cresol.eventsTest.service;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class EventPublisher {
    private static final String EXCHANGE_NAME = "event.exchange";

    public void publishEvent(String eventId, Date initialDate, Date finalDate) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(var connection = factory.newConnection();
            var channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false,
                    Map.of("x-delayed-type", "direct"));
            long activationDelay = ChronoUnit.MILLIS.between(LocalDateTime.now(), initialDate.toInstant());
            publishMessage(channel, "event.activate", eventId, activationDelay, "ativado!");

            long deactivationDelay = ChronoUnit.MILLIS.between(LocalDateTime.now(), finalDate.toInstant());
            publishMessage(channel, "event.deactivate", eventId, deactivationDelay, "desativado!");
        }
    }

    public void publishMessage(Channel channel, String routingKey, String eventId, long delay, String action) throws Exception{
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .headers(Map.of("x-delay", delay))
                .build();
        String message = action + " Evento: " + eventId;
        channel.basicPublish(EXCHANGE_NAME, routingKey, properties, message.getBytes(StandardCharsets.UTF_8));
    }
}
