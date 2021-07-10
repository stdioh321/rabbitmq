package com.stdioh.rabbitmq.rabbitmq.receiver;

import com.stdioh.rabbitmq.rabbitmq.model.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMQReceiver {

    @RabbitListener(queues = {"myQueue"})
    public void listen(Message in) {

        System.out.println("Message read from myQueue : " + new String(in.getBody(), StandardCharsets.UTF_8));
    }
}
