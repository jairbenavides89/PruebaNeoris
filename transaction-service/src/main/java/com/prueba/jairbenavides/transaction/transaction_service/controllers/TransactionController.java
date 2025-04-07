package com.prueba.jairbenavides.transaction.transaction_service.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;
import com.prueba.jairbenavides.transaction.transaction_service.services.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/{accountId}")
    public ResponseEntity<?> create(@PathVariable Long accountId
    , @Valid @RequestBody Transaction transaction
    , BindingResult result) throws Exception {
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(accountId, transaction));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
