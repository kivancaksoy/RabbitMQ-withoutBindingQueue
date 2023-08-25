package com.folksdev.rabbitwhitoutbinding.service;

import com.folksdev.rabbitwhitoutbinding.Message;
import com.folksdev.rabbitwhitoutbinding.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageListener {

    private final RabbitTemplate rabbitTemplate;
    private final MessageRepository messageRepository;

    public MessageListener(RabbitTemplate rabbitTemplate, MessageRepository messageRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageRepository = messageRepository;
    }

    public List<Message> messages() {
        return messageRepository.findAll();
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void receiveMessages(String message) {
        messageRepository.save(new Message(null, message));
    }
}
