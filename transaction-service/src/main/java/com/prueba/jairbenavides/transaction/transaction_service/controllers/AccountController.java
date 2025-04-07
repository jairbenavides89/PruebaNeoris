package com.prueba.jairbenavides.transaction.transaction_service.controllers;

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

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public List<Account> list() {
        return service.findAll();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        Optional<Account> accountOptional = service.getAccountById(accountId);
        if (accountOptional.isPresent()) {
            return ResponseEntity.ok(accountOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Account account, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(account));
    }

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

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> delete(@PathVariable Long accountId) {
        Optional<Account> accountOptional = service.delete(accountId);
        if (accountOptional.isPresent()) {
            return ResponseEntity.ok(accountOptional.orElseThrow());
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
