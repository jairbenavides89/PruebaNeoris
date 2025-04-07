package com.prueba.jairbenavides.clients.client_service.repositories;

import org.springframework.data.repository.CrudRepository;
import com.prueba.jairbenavides.clients.client_service.entities.Person;

/**
 * Repositorio para la entidad {@link Person}.
 * <p>
 * Esta interfaz extiende de {@link CrudRepository} para proporcionar operaciones CRUD básicas
 * sobre la entidad {@link Person}.
 * </p>
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
}
