package com.prueba.jairbenavides.clients.client_service.controllers;

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

import com.prueba.jairbenavides.clients.client_service.entities.Client;
import com.prueba.jairbenavides.clients.client_service.services.ClientService;

import jakarta.validation.Valid;

/**
 * Controlador REST para la gestión de clientes.
 * <p>
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad {@link Client}.
 * </p>
 */
@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientService service;

    /**
     * Recupera la lista de todos los clientes.
     *
     * @return una lista de clientes.
     */
    @GetMapping
    public List<Client> list() {
        return service.findAll();
    }

    /**
     * Recupera un cliente específico por su ID.
     *
     * @param clientid el identificador del cliente.
     * @return una respuesta HTTP que contiene el cliente si se encontró, o 404 Not Found si no existe.
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable Long clientid) {
        Optional<Client> clientOptional = service.getClientById(clientid);
        if (clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param client el objeto {@link Client} a crear.
     * @param result el resultado de la validación de los datos del cliente.
     * @return una respuesta HTTP con el cliente creado o un error de validación.
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(client));
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param client   el objeto {@link Client} con la nueva información.
     * @param result   el resultado de la validación de los datos del cliente.
     * @param clientId el identificador del cliente a actualizar.
     * @return una respuesta HTTP con el cliente actualizado, o 404 Not Found si el cliente no existe.
     */
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

    /**
     * Elimina un cliente existente.
     *
     * @param clientId el identificador del cliente a eliminar.
     * @return una respuesta HTTP con el cliente eliminado, o 404 Not Found si el cliente no existe.
     */
    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> delete(@PathVariable Long clientId) {
        Optional<Client> clientOptional = service.delete(clientId);
        if (clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Valida los errores de validación y construye una respuesta HTTP adecuada.
     *
     * @param result el resultado de la validación.
     * @return una respuesta HTTP con los errores de validación.
     */
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
