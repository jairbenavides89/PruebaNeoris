package com.prueba.jairbenavides.clients.client_service.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
/*import com.prueba.jairbenavides.clients.client_service.events.ClientCreatedEvent;
*/
/*import static com.prueba.jairbenavides.clients.client_service.config.MessageQueueConfig.CLIENT_CREATED_QUEUE;
*/
/**
 * Servicio encargado de publicar eventos relacionados con la creación de clientes en la cola de mensajes.
 * <p>
 * Utiliza {@link RabbitTemplate} para enviar mensajes que notifiquen la creación de un cliente.
 * </p>
 */

public class ClientEventPublisherService {

    // Instancia de RabbitTemplate utilizada para enviar mensajes a la cola.
    /*private final RabbitTemplate template = new RabbitTemplate();
    */
    /**
     * Publica un evento de creación de cliente en la cola de mensajes configurada.
     * <p>
     * Este método crea un objeto {@link ClientCreatedEvent} con la información del cliente y lo envía
     * a la cola definida en {@link com.prueba.jairbenavides.clients.client_service.config.MessageQueueConfig#CLIENT_CREATED_QUEUE}.
     * </p>
     *
     * @param clientId       el identificador único del cliente.
     * @param name           el nombre del cliente.
     * @param identification la identificación del cliente.
     */
    /*public void publishClientCreatedEvent(Long clientId, String name, String identification) {
        ClientCreatedEvent event = new ClientCreatedEvent(clientId, name, identification);
        template.convertAndSend(CLIENT_CREATED_QUEUE, event);
    }/* */
}
