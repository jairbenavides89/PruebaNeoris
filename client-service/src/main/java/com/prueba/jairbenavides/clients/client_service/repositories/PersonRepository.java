package com.prueba.jairbenavides.clients.client_service.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prueba.jairbenavides.clients.client_service.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

}
