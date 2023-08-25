package com.folksdev.rabbitwhitoutbinding.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final AmqpTemplate rabbitTemplate;

    @Value("${sample.rabbitmq.exchange}")
    String exchange;

    @Value("${sample.rabbitmq.routingKey}")
    String routingKey;

    public HelloService(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sentRabbitMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
