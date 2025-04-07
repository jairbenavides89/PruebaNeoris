package com.prueba.jairbenavides.clients.client_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.jairbenavides.clients.client_service.entities.Client;
import com.prueba.jairbenavides.clients.client_service.repositories.ClientRepository;
import com.prueba.jairbenavides.clients.client_service.repositories.PersonRepository;

/**
 * Implementación del servicio para la gestión de clientes.
 * <p>
 * Esta clase implementa la interfaz {@link ClientService} y proporciona la lógica de negocio para
 * la creación, actualización, eliminación y consulta de clientes. Además, gestiona la persistencia
 * de la información personal asociada a cada cliente a través de {@link PersonRepository}.
 * </p>
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Recupera la lista de todos los clientes.
     *
     * @return una lista de clientes.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) repository.findAll();
    }

    /**
     * Guarda un nuevo cliente junto con la información personal asociada.
     *
     * @param client el cliente a guardar.
     * @return el cliente guardado.
     */
    @Override
    @Transactional
    public Client save(Client client) {
        personRepository.save(client.getPerson());
        return repository.save(client);
    }

    /**
     * Actualiza la información de un cliente existente identificado por su ID.
     * <p>
     * Se actualiza tanto la información del cliente como la de la persona asociada.
     * </p>
     *
     * @param clientId el identificador del cliente a actualizar.
     * @param client   el objeto {@link Client} con la información actualizada.
     * @return un {@link Optional} que contiene el cliente actualizado, o vacío si no se encontró el cliente.
     */
    @Override
    @Transactional
    public Optional<Client> update(Long clientId, Client client) {
        Optional<Client> clientOptional = repository.findById(clientId);
        if (clientOptional.isPresent()) {
            Client clientDb = clientOptional.orElseThrow();
            clientDb.getPerson().setAddress(client.getPerson().getAddress());
            clientDb.getPerson().setAge(client.getPerson().getAge());
            clientDb.getPerson().setGender(client.getPerson().getGender());
            clientDb.getPerson().setTelephone(client.getPerson().getTelephone());
            clientDb.setPassword(client.getPassword());
            clientDb.setStatus(client.getStatus());
            personRepository.save(clientDb.getPerson());
            return Optional.of(repository.save(clientDb));
        }
        return clientOptional;
    }

    /**
     * Elimina un cliente del sistema identificado por su ID.
     *
     * @param clientId el identificador del cliente a eliminar.
     * @return un {@link Optional} que contiene el cliente eliminado, o vacío si no se encontró el cliente.
     */
    @Override
    @Transactional
    public Optional<Client> delete(Long clientId) {
        Optional<Client> clientOptional = repository.findById(clientId);
        clientOptional.ifPresent(client -> repository.delete(client));
        return clientOptional;
    }

    /**
     * Recupera un cliente específico por su ID.
     *
     * @param clientId el identificador del cliente a recuperar.
     * @return un {@link Optional} que contiene el cliente, o vacío si no se encontró.
     */
    @Override
    public Optional<Client> getClientById(Long clientId) {
        Optional<Client> clientOptional = repository.findById(clientId);
        if (clientOptional.isPresent()) {
            return Optional.of(clientOptional.orElseThrow());
        }
        return clientOptional;
    }
}
