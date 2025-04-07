package com.prueba.jairbenavides.clients.client_service.repositories;

import org.springframework.data.repository.CrudRepository;
import com.prueba.jairbenavides.clients.client_service.entities.Client;

/**
 * Repositorio para la entidad {@link Client}.
 * <p>
 * Esta interfaz extiende de {@link CrudRepository} para proporcionar las operaciones CRUD básicas
 * para la entidad {@link Client}.
 * </p>
 */
public interface ClientRepository extends CrudRepository<Client, Long> {
}
