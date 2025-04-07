package com.prueba.jairbenavides.transaction.transaction_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de RabbitMQ para el servicio de transacciones.
 * Define colas, conversores de mensajes y plantillas para enviar mensajes.
 */
@Configuration
public class MessageQueueConfig {

    /**
     * Nombre de la cola utilizada para recibir eventos cuando se crea un cliente.
     */
    public static final String CLIENT_CREATED_QUEUE = "client.created.queue";

    /**
     * Crea una cola durable para eventos de creación de clientes.
     *
     * @return instancia de {@link Queue} para client.created.queue
     */
    @Bean
    public Queue clientCreatedQueue() {
        return new Queue(CLIENT_CREATED_QUEUE, true);
    }

    /**
     * Configura un convertidor de mensajes que usa JSON con Jackson.
     * Permite convertir automáticamente objetos Java a JSON y viceversa.
     *
     * @return instancia de {@link Jackson2JsonMessageConverter}
     */
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Crea un {@link RabbitTemplate} con un convertidor JSON.
     * Usado para enviar mensajes a RabbitMQ de forma programática.
     *
     * @param connectionFactory fábrica de conexiones a RabbitMQ
     * @return una instancia configurada de {@link RabbitTemplate}
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
