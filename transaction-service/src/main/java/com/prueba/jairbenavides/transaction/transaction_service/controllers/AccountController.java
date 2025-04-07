package com.prueba.jairbenavides.transaction.transaction_service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.services.AccountService;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar operaciones relacionadas con cuentas.
 * Provee endpoints CRUD para listar, crear, actualizar y eliminar cuentas.
 */
@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private AccountService service;

    /**
     * Obtiene la lista de todas las cuentas.
     *
     * @return una lista de objetos {@link Account}
     */
    @GetMapping
    public List<Account> list() {
        return service.findAll();
    }

    /**
     * Obtiene una cuenta por su ID.
     *
     * @param accountId ID de la cuenta
     * @return una respuesta HTTP con la cuenta encontrada o error 404 si no existe
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        Optional<Account> accountOptional = service.getAccountById(accountId);
        if (accountOptional.isPresent()) {
            return ResponseEntity.ok(accountOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Crea una nueva cuenta.
     *
     * @param account objeto {@link Account} con los datos de la cuenta
     * @param result  validaciones de entrada
     * @return una respuesta HTTP con la cuenta creada o errores de validación
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Account account, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(account));
    }

    /**
     * Actualiza una cuenta existente.
     *
     * @param accountId ID de la cuenta a actualizar
     * @param account   objeto {@link Account} con los nuevos datos
     * @param result    validaciones de entrada
     * @return una respuesta HTTP con la cuenta actualizada o errores
     */
    @PutMapping("/{accountId}")
    public ResponseEntity<?> update(@Valid @RequestBody Account account, BindingResult result,
                                    @PathVariable Long accountId) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Account> accountOptional = service.update(accountId, account);
        if (accountOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(accountOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Elimina una cuenta por su ID.
     *
     * @param accountId ID de la cuenta a eliminar
     * @return una respuesta HTTP con la cuenta eliminada o error si no se encontró
     */
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> delete(@PathVariable Long accountId) {
        Optional<Account> accountOptional = service.delete(accountId);
        if (accountOptional.isPresent()) {
            return ResponseEntity.ok(accountOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Procesa errores de validación de entrada.
     *
     * @param result resultados de validación
     * @return respuesta HTTP con el mapa de errores
     */
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
