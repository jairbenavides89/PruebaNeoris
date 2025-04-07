package com.prueba.jairbenavides.transaction.transaction_service.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;
import com.prueba.jairbenavides.transaction.transaction_service.services.TransactionService;

import jakarta.validation.Valid;

/**
 * Controlador REST para manejar operaciones relacionadas con transacciones.
 */
@RestController
@RequestMapping("/movimientos")
public class TransactionController {

    @Autowired
    private TransactionService service;

    /**
     * Crea una nueva transacción asociada a una cuenta existente.
     *
     * @param accountId   ID de la cuenta a la cual se asociará la transacción
     * @param transaction objeto {@link Transaction} que contiene los datos de la transacción
     * @param result      objeto para capturar errores de validación
     * @return ResponseEntity con la transacción creada o errores de validación
     * @throws Exception en caso de errores durante la creación de la transacción
     */
    @PostMapping("/{accountId}")
    public ResponseEntity<?> create(
            @PathVariable Long accountId,
            @Valid @RequestBody Transaction transaction,
            BindingResult result) throws Exception {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(accountId, transaction));
    }

    /**
     * Procesa y retorna errores de validación en un formato comprensible.
     *
     * @param result resultado de la validación
     * @return ResponseEntity con los errores de validación
     */
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
