package com.prueba.jairbenavides.clients.client_service.services;

import java.util.List;
import java.util.Optional;

import com.prueba.jairbenavides.clients.client_service.entities.Client;

/**
 * Interfaz de servicio para la gestión de clientes.
 * <p>
 * Define las operaciones de negocio relacionadas con la entidad {@link Client},
 * incluyendo la creación, actualización, eliminación y consulta de clientes.
 * </p>
 */
public interface ClientService {

    /**
     * Recupera la lista de todos los clientes.
     *
     * @return una lista con todos los clientes.
     */
    List<Client> findAll();

    /**
     * Guarda un nuevo cliente en el sistema.
     *
     * @param client el cliente a guardar.
     * @return el cliente guardado.
     */
    Client save(Client client);

    /**
     * Actualiza la información de un cliente existente identificado por su ID.
     *
     * @param clientId el identificador del cliente a actualizar.
     * @param client   el objeto {@link Client} con la información actualizada.
     * @return un {@link Optional} que contiene el cliente actualizado, o vacío si no se encontró el cliente.
     */
    Optional<Client> update(Long clientId, Client client);

    /**
     * Elimina un cliente del sistema identificado por su ID.
     *
     * @param clientId el identificador del cliente a eliminar.
     * @return un {@link Optional} que contiene el cliente eliminado, o vacío si no se encontró el cliente.
     */
    Optional<Client> delete(Long clientId);

    /**
     * Recupera un cliente específico por su ID.
     *
     * @param clientId el identificador del cliente a recuperar.
     * @return un {@link Optional} que contiene el cliente, o vacío si no se encontró.
     */
    Optional<Client> getClientById(Long clientId);
}
