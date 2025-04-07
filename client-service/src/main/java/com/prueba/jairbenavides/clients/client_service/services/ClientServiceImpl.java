package com.prueba.jairbenavides.clients.client_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.jairbenavides.clients.client_service.entities.Client;
import com.prueba.jairbenavides.clients.client_service.repositories.ClientRepository;
import com.prueba.jairbenavides.clients.client_service.repositories.PersonRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) repository.findAll();
    }

    @Override
    @Transactional
    public Client save(Client client) {
        personRepository.save(client.getPerson());
        return repository.save(client);
    }

    @Override
    @Transactional
    public Optional<Client> update(Long clientId, Client client) {
        Optional<Client> clientOptional = repository.findById(clientId);
        if(clientOptional.isPresent()){
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
    
    @Override
    @Transactional
    public Optional<Client> delete(Long clientId) {
        Optional<Client> clientOptional = repository.findById(clientId);
        clientOptional.ifPresent(client -> {
            repository.delete(client);
        });

        return clientOptional;
    }

    @Override
    public Optional<Client> getClientById(Long clientId) {
        Optional<Client> clientOptional = repository.findById(clientId);
        if(clientOptional.isPresent()){
            return Optional.of(clientOptional.orElseThrow());
        }

        return clientOptional;
    }

}
