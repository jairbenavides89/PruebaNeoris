package com.prueba.jair.benavides.clientsaccounts.clients_accounts.services;

import java.util.List;
import java.util.Optional;

import com.prueba.jair.benavides.clientsaccounts.clients_accounts.entities.Client;

public interface ClientService {

    List<Client> findAll();

    Client save(Client client);

    Optional<Client> update(Long clientId, Client client);

    Optional<Client> delete(Long clientId);

    Optional<Client> getClientById(Long clientId);

}
