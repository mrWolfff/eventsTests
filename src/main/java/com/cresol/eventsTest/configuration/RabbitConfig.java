package com.cresol.eventsTest.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "events.exchange";
    public static final String QUEUE_NAME = "events.queue";

    @Bean
    public CustomExchange delayedExchange() {
        return new CustomExchange(EXCHANGE_NAME, "x-delayed-message", true, false,
                Map.of("x-delayed-type", "direct"));
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue queue, CustomExchange delayedExchange) {
        return BindingBuilder.bind(queue).to(delayedExchange).with("").noargs();
    }
}
