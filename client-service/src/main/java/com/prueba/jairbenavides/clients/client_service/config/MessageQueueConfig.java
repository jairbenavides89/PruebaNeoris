package com.prueba.jairbenavides.clients.client_service.config;

/*import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;*/

/**
 * Configuración de la cola de mensajes y del template de RabbitMQ.
 * <p>
 * Esta clase configura los componentes necesarios para la comunicación con RabbitMQ,
 * incluyendo la definición de la cola para eventos de creación de clientes y el conversor
 * de mensajes a formato JSON.
 * </p>
 */
public class MessageQueueConfig {

    /**
     * Nombre de la cola para eventos de creación de clientes.
     */
    /*public static final String CLIENT_CREATED_QUEUE = "client.created.queue";

    /**
     * Declara y configura la cola de mensajes para la creación de clientes.
     *
     * @return una instancia de {@link Queue} con persistencia activada.
     */
    /*@Bean
    public Queue clientCreatedQueue() {
        return new Queue(CLIENT_CREATED_QUEUE, true);
    }*/

    /**
     * Configura el conversor de mensajes que utiliza JSON.
     *
     * @return una instancia de {@link Jackson2JsonMessageConverter} para convertir mensajes.
     */
    /*@Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }*/

    /**
     * Configura el {@link RabbitTemplate} para enviar y recibir mensajes a través de RabbitMQ.
     * <p>
     * Se establece el conversor de mensajes para que los mensajes se serialicen y deserialicen en JSON.
     * </p>
     *
     * @param connectionFactory la fábrica de conexiones a RabbitMQ.
     * @return una instancia de {@link RabbitTemplate} configurada con el conversor JSON.
     */
    /*@Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }*/
}
