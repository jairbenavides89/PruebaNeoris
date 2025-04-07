package com.prueba.jairbenavides.clients.client_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.jairbenavides.clients.client_service.entities.Client;
import com.prueba.jairbenavides.clients.client_service.entities.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void shouldCreateClientSuccessfully() {
        Client client = clientService.save(new Client("abcd1234",
        true,
        new Person("John Doe","Masculino",30,"1234567890","Calle A","09912345678")));

        assertNotNull(client);
        assertEquals("John Doe", client.getPerson().getName());

        Optional<Client> clientoOptional = clientService.getClientById(client.getClienteid());

        Client clientDb = new Client();

        if(clientoOptional.isPresent()){
            clientDb = clientoOptional.orElseThrow();
        } else {
           throw new RuntimeException("Cliente no existe en la Bdd");
        }

        assertNotNull(clientDb);
        assertEquals("John Doe", clientDb.getPerson().getName());

    }
}
