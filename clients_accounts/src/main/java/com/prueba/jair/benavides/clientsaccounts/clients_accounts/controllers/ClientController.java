package com.prueba.jair.benavides.clientsaccounts.clients_accounts.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.jair.benavides.clientsaccounts.clients_accounts.entities.Client;
import com.prueba.jair.benavides.clientsaccounts.clients_accounts.services.ClientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/clientes")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> list(){
        return service.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable Long clientid){
        Optional<Client> clientOptional = service.getClientById(clientid);
        if(clientOptional.isPresent()){
            return ResponseEntity.ok(clientOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(client));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long clientId) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Client> clientOptional = service.update(clientId, client);
        if (clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> delete(@PathVariable Long clientId) {
        Optional<Client> clientOptional = service.delete(clientId);
        if (clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

     private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
