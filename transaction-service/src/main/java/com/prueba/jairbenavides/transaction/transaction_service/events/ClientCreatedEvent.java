package com.prueba.jairbenavides.transaction.transaction_service.events;

/**
 * Representa un evento que se dispara cuando se crea un cliente.
 * <p>
 * Este record almacena la información esencial del cliente creado.
 *
 * @param id              el identificador único del cliente.
 * @param name            el nombre del cliente.
 * @param identificaciton la identificación del cliente.
 */
public record ClientCreatedEvent(Long id, String name, String identificaciton) {

    /**
     * Devuelve el identificador único del cliente.
     *
     * @return el id del cliente.
     */
    public Long id() {
        return id;
    }

    /**
     * Devuelve el nombre del cliente.
     *
     * @return el nombre del cliente.
     */
    public String name() {
        return name;
    }

    /**
     * Devuelve la identificación del cliente.
     *
     * @return la identificación del cliente.
     */
    public String identificaciton() {
        return identificaciton;
    }

}
