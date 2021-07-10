package com.stdioh.rabbitmq.rabbitmq.controller;

import com.stdioh.rabbitmq.rabbitmq.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RabbitMQController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/message/{message}")
    public ResponseEntity sendMessage(@PathVariable(value = "message", required = false) String message) {
        if (message == null) message = "Hello, world!";
        rabbitTemplate.convertAndSend("myQueue", message);
        return ResponseEntity.ok("Message: " + message + " successfully sent");
    }
    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity post(@RequestBody User user) {
        rabbitTemplate.convertAndSend("myQueue", user);
        return ResponseEntity.ok("Message: " + user + " successfully sent");
    }
}
