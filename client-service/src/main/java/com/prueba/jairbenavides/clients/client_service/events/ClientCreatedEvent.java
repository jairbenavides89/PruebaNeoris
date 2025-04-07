package com.prueba.jairbenavides.clients.client_service.events;

/**
 * Evento que representa la creación de un cliente.
 * <p>
 * Este record encapsula la información esencial de un cliente creado, incluyendo su identificador,
 * nombre e identificación.
 * </p>
 *
 * @param id              el identificador único del cliente.
 * @param name            el nombre del cliente.
 * @param identificaciton la identificación del cliente.
 */
public record ClientCreatedEvent(Long id, String name, String identificaciton) {
}
