package com.folksdev.rabbitwhitoutbinding.controller;

import com.folksdev.rabbitwhitoutbinding.Message;
import com.folksdev.rabbitwhitoutbinding.service.HelloService;
import com.folksdev.rabbitwhitoutbinding.service.MessageListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rabbitmq")
public class HelloController {

    private final HelloService helloService;
    private final MessageListener messageListener;

    public HelloController(HelloService helloService, MessageListener messageListener) {
        this.helloService = helloService;
        this.messageListener = messageListener;
    }

    @GetMapping
    public ResponseEntity<String> helloWorld(@RequestParam(value = "message") String message) {

        helloService.sentRabbitMessage(message);
        return ResponseEntity.ok("Mesaj alındı");
    }

    @GetMapping("/m")
    public ResponseEntity<List<Message>> receiveMessages() {
        return ResponseEntity.ok(messageListener.messages());
    }
}
