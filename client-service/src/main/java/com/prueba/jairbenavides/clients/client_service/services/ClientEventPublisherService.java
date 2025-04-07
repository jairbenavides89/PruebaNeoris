package com.prueba.jairbenavides.clients.client_service.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.prueba.jairbenavides.clients.client_service.events.ClientCreatedEvent;

import static com.prueba.jairbenavides.clients.client_service.config.MessageQueueConfig.CLIENT_CREATED_QUEUE;

@Service
public class ClientEventPublisherService {

    private final RabbitTemplate template = new RabbitTemplate();

    public void publishClientCreatedEvent(Long clientId, String name, String identification) {
        ClientCreatedEvent event = new ClientCreatedEvent(clientId, name, identification);

        template.convertAndSend(CLIENT_CREATED_QUEUE, event);
    }

}
